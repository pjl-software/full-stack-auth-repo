package com.pjlsoftware.service;

import com.pjlsoftware.authenticationConstants.RoleName;
import com.pjlsoftware.entity.Role;
import com.pjlsoftware.entity.User;
import com.pjlsoftware.projection.AuthenticatedUserProjection;
import com.pjlsoftware.repository.RoleRepository;
import com.pjlsoftware.repository.UserRepository;
import com.pjlsoftware.security.ValidateGoogleAuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Optional<User> softDeleteUser(String username) {
        try {
            User userToDelete = userRepository.findByUsernameAndEnabledIsTrue(username)
                    .orElseThrow(() -> new RuntimeException("No enabled user found to delete with username " + username));
            userToDelete.setEnabled(false);
            userToDelete.setRoles(new HashSet<>(Set.of()));

            return Optional.of(userRepository.update(userToDelete));
        } catch (Exception e) {
            LOGGER.info("exception in softDeleteUser: {}", e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    @Override
    public User getUserFromJwt(JwtAuthenticationToken principal) {
        try {
            User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(principal.getToken().getTokenValue())
                    .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));
            return userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Unable to getUserFromJwt"));
        } catch (Exception e) {
            LOGGER.info("Exception in getUserFromJwt: {}", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Optional<AuthenticatedUserProjection> getUserInformation(String username) {
        try {
            return userRepository.returnUserInfoForEnabledUserByUsername(username);
        } catch (Exception e) {
            LOGGER.info("Exception in getUserInformation: {}", e.getLocalizedMessage());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public ResponseEntity<AuthenticatedUserProjection> handleGoogleSignIn(final String jwt) {
        try {
            User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(jwt)
                    .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));

            Role freeUserRole = roleRepository.findByName(RoleName.ROLE_FREE_USER)
                    .orElseThrow(() -> new RuntimeException("Couldn't find the " + RoleName.ROLE_FREE_USER +
                            " role. Are you sure you loaded the database?"));
            Set<Role> googleUserRoles = new HashSet<>(Set.of(freeUserRole));

            Optional<User> isAlreadyUser = userRepository.findByUsername(user.getUsername());

            if (isAlreadyUser.isPresent()) {
                User existingUser = isAlreadyUser.get();
                if (existingUser.isEnabled()) {
                    // do nothing special
                } else {
                    LOGGER.info("Automatically re-enabling a disabled user. Are we sure we want to do this?");
                    existingUser.setEnabled(true);
                    existingUser.setRoles(googleUserRoles);
                }
                userRepository.update(existingUser);
            } else {
                LOGGER.info("Creating new Google user.");
                user.setRoles(googleUserRoles);
                userRepository.persist(user);
            }

            return new ResponseEntity<>(userRepository.returnUserInfoForEnabledUserByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("Failed to get username"))
                    , HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info("Exception in handleGoogleSignIn: {}", e.getLocalizedMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public Optional<AuthenticatedUserProjection> toggleAdminStatus(String username) {
        try {
            User validUser = userRepository.findByUsernameAndEnabledIsTrue(username)
                    .orElseThrow(() -> new RuntimeException(username + " is not a valid username."));
            Role adminUserRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Couldn't find the " + RoleName.ROLE_ADMIN +
                            " role. Are you sure you loaded the database?"));

            if (validUser.getRoles().contains(adminUserRole)) {
                validUser.getRoles().remove(adminUserRole);
            } else {
                validUser.getRoles().add(adminUserRole);
            }
            userRepository.persist(validUser);
            return userRepository.returnUserInfoForEnabledUserByUsername(username);
        } catch (Exception e) {
            LOGGER.info("Exception in toggleAdminStatus. {}", e.getLocalizedMessage());
        }
        return Optional.empty();
    }
}

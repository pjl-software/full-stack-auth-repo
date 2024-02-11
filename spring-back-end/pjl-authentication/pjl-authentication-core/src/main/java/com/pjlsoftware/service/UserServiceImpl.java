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
    public User getUserFromJwt(JwtAuthenticationToken principal) {
        try {
            User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(principal.getToken().getTokenValue())
                    .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));
            return userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Unable to getUserFromJwt"));
        } catch (Exception e) {
            LOGGER.info("Exception in getUserFromJwt", e);
        }
        return new User();
    }

    @Override
    public Optional<AuthenticatedUserProjection> getUserInformation(String username) {
        try {
            return userRepository.returnUserInfoForEnabledUserByUsername(username);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<AuthenticatedUserProjection> handleGoogleSignIn(final String jwt) {
        try {
            LOGGER.info("Made it here...");
            User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(jwt)
                    .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));

            Role freeUserRole = roleRepository.findByName(RoleName.ROLE_FREE_USER)
                    .orElseThrow(() -> new RuntimeException("Couldn't find the " + RoleName.ROLE_FREE_USER +
                            " role. Are you sure you loaded the database?"));
            Role adminUserRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Couldn't find the " + RoleName.ROLE_ADMIN +
                            " role. Are you sure you loaded the database?"));
            Set<Role> googleUserRoles = new HashSet<>(Set.of(freeUserRole));

            Optional<User> isAlreadyUser = userRepository.findByUsername(user.getUsername());

            if (isAlreadyUser.isPresent()) {
                User existingUser = isAlreadyUser.get();

                if (existingUser.isEnabled()) {

                } else {
                    existingUser.setEnabled(true);
                    existingUser.setRoles(googleUserRoles);
                    userRepository.saveAndFlush(existingUser);

                }
            } else {
                user.setRoles(googleUserRoles);
                userRepository.saveAndFlush(user);
            }

            return new ResponseEntity<>(userRepository.returnUserInfoForEnabledUserByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("Failed to get username"))
                    , HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.info("Exception in handleGoogleSignIn", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

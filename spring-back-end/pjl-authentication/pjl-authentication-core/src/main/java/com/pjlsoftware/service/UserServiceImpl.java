package com.pjlsoftware.service;

import com.pjlsoftware.authenticationConstants.RoleName;
import com.pjlsoftware.entity.Role;
import com.pjlsoftware.entity.User;
import com.pjlsoftware.repository.RoleRepository;
import com.pjlsoftware.repository.UserRepository;
import com.pjlsoftware.security.ValidateGoogleAuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> handleGoogleSignIn(final String jwt) {
        try {
            User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(jwt)
                    .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));

            Role freeUserRole = roleRepository.findByName(RoleName.ROLE_FREE_USER)
                    .orElseThrow(() -> new RuntimeException("Couldn't find the " + RoleName.ROLE_FREE_USER +
                            " role. Are you sure you loaded the database?"));

            Optional<User> isAlreadyUser = userRepository.findByUsername(user.getUsername());

            if (isAlreadyUser.isPresent()) {
                User existingUser = isAlreadyUser.get();

                if (existingUser.isEnabled()) {
                    return new ResponseEntity<>("{\"value\": \"Logging in active user\"}", HttpStatus.CREATED);
                } else {
                    existingUser.setEnabled(true);
                    existingUser.setRoles(new HashSet<>(Set.of(freeUserRole)));

                    userRepository.saveAndFlush(existingUser);
                    return new ResponseEntity<>("{\"value\": \"Re-enabled existing user\"}", HttpStatus.CREATED);
                }
            } else {
                user.setRoles(new HashSet<>(Set.of(freeUserRole)));

                userRepository.saveAndFlush(user);
                return new ResponseEntity<>("{\"value\": \"Created new user\"}", HttpStatus.CREATED);
            }
        } catch (Exception e) {
            LOGGER.info("Exception in handleGoogleSignIn", e);
            return new ResponseEntity<>("{\"value\": \"Google sign-in failed.\"}", HttpStatus.BAD_REQUEST);
        }
    }
}

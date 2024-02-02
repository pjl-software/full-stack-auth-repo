package com.pjlsoftware.controller.security;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.projection.UserProjection;
import com.pjlsoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(
            value = "/create/google",
            method = RequestMethod.POST,
            produces = {"application/json"}
    )
    public ResponseEntity<String> createGoogleUser() {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String bearerToken = jwtAuthenticationToken.getToken().getTokenValue();

        User user = ValidateGoogleAuthToken.verifyGoogleAuthToken(bearerToken)
                .orElseThrow(() -> new RuntimeException("Failed to validate JWT."));

        Optional<User> isAlreadyUser = userRepository.findByUsername(user.getUsername());

        if (isAlreadyUser.isPresent()) {
            User existingUser = isAlreadyUser.get();
            existingUser.setEnabled(true);
            userRepository.saveAndFlush(existingUser);
            return new ResponseEntity<>("{\"value\": \"Re-enabled existing user\"}", HttpStatus.CREATED);
        } else {
            userRepository.saveAndFlush(user);
            return new ResponseEntity<>("{\"value\": \"Created new user\"}", HttpStatus.CREATED);
        }
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = {"application/json"}
    )
    public ResponseEntity<String> createUser() {
        userRepository.saveAndFlush(new User());
        return new ResponseEntity<>("{\"value\": \"Created new user\"}", HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/delete/{username}",
            method = RequestMethod.PUT,
            produces = {"application/json"}
    )
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            User existingUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("No user found with username: " + username));
            existingUser.setEnabled(false);
            userRepository.saveAndFlush(existingUser);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"value\": \"Nothing done. Check Logs.\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("{\"value\": \"" + username + " has been disabled.\"}", HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    public ResponseEntity<List<UserProjection>> getEnabledUsers() {
        return new ResponseEntity<>(userRepository.findByEnabledIsTrue()
                .orElseThrow(() -> new RuntimeException("No enabled users found.")), HttpStatus.OK);
    }
}
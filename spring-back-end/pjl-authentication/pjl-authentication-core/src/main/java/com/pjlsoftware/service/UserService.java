package com.pjlsoftware.service;

import org.springframework.http.ResponseEntity;

public interface UserService {

    /**
     * Validate Google JWT, create User Entity from JWT info then:
     * - Log in existing user OR
     * - Re-enable disabled user adding back role(s) OR
     * - Create new user with role(s)
     *
     * @param jwt - Google JWT
     * @return - Response entity with JSON string indicating status.
     */
    ResponseEntity<String> handleGoogleSignIn(final String jwt);
}

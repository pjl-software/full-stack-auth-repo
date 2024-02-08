package com.pjlsoftware.service;

import com.pjlsoftware.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

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

    /**
     * @param principal - Value from @AuthenticationPrincipal
     * @return - A valid user from our database associated with the principal; otherwise null
     */
    User getUserFromJwt(JwtAuthenticationToken principal);
}

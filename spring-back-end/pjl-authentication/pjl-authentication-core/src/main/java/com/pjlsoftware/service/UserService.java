package com.pjlsoftware.service;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.projection.AuthenticatedUserProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public interface UserService {

    /**
     * @param principal - Value from @AuthenticationPrincipal
     * @return - A valid user from our database associated with the principal; otherwise null
     */
    User getUserFromJwt(JwtAuthenticationToken principal);

    /**
     *
     * @param username
     * @return
     */
    Optional<AuthenticatedUserProjection> getUserInformation(String username);

    /**
     * Validate Google JWT, create User Entity from JWT info then:
     * - Log in existing user OR
     * - Re-enable disabled user adding back role(s) OR
     * - Create new user with role(s)
     *
     * @param jwt - Google JWT
     * @return - Response entity with JSON string indicating status.
     */
    ResponseEntity<AuthenticatedUserProjection> handleGoogleSignIn(final String jwt);
}

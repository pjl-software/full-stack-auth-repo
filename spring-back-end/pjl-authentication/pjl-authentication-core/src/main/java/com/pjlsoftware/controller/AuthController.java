package com.pjlsoftware.controller;

import com.pjlsoftware.projection.AuthenticatedUserProjection;
import com.pjlsoftware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/sign-in/google",
            method = RequestMethod.POST,
            produces = {"application/json"}
    )
    public ResponseEntity<AuthenticatedUserProjection> createGoogleUser() {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String bearerToken = jwtAuthenticationToken.getToken().getTokenValue();

        return this.userService.handleGoogleSignIn(bearerToken);
    }
}

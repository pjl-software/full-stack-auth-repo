package com.pjlsoftware.service;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.security.ValidateGoogleAuthToken;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userFromToken = ValidateGoogleAuthToken.verifyGoogleAuthToken(jwtAuthenticationToken.getToken().getTokenValue());

        return userFromToken.map(User::getUsername).or(() -> Optional.of("unknown"));
    }
}

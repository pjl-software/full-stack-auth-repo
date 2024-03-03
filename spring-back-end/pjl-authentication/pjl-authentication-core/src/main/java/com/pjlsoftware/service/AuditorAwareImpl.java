package com.pjlsoftware.service;

import com.pjlsoftware.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

public class AuditAwareImpl implements AuditorAware<User> {
    private final UserService userService;

    @Autowired
    public AuditAwareImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return (userService.getUserFromJwt(jwtAuthenticationToken) == null) ? Optional.empty() : Optional.of(userService.getUserFromJwt(jwtAuthenticationToken));
    }
}

package com.pjlsoftware.projection;

import java.time.Instant;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

public interface AuthenticatedUserProjection {
    String getFirstName();

    String getLastName();

    String getUsername();

    String getPhotoUrl();

    Instant getLastLogin();

    @Value("#{target.getRoleNames}")
    Set<String> getRoleNames();

    @Value("#{target.getIsAuthenticated}")
    Boolean getIsAuthenticated();

    @Value("#{target.getIsNotAuthenticated}")
    Boolean getIsNotAuthenticated();

    @Value("#{target.getIsAdmin}")
    Boolean getIsAdmin();

    @Value("#{target.getIsNotAdmin}")
    Boolean getIsNotAdmin();
}

package com.pjlsoftware.projection;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {
    String getFirstName();

    String getLastName();

    String getUsername();

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

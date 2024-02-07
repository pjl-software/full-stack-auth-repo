package com.pjlsoftware.projection;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {
    String getFirstName();

    String getLastName();

    String getUsername();

    @Value("#{target.getRoleNames}")
    Set<String> getRoleNames();
}

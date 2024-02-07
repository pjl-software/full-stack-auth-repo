package com.pjlsoftware.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

public interface UserProjection {
    String getFirstName();

    String getLastName();

    String getUsername();

    @Value("#{target.getRoleNames}")
    Set<String> getRoleNames();
}

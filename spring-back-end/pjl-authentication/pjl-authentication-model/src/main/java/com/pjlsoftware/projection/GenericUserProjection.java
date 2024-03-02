package com.pjlsoftware.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

public interface GenericUserProjection {
    String getFirstName();

    String getLastName();

    String getUsername();

    @Value("#{target.getRoleNames}")
    Set<String> getRoleNames();
}

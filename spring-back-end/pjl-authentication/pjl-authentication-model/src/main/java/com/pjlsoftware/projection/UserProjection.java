package com.pjlsoftware.projection;

import com.pjlsoftware.entity.Role;

import java.util.Set;

public interface UserProjection {
    String getFirstName();

    String getLastName();

    String getUsername();

    Set<RoleProjection> getRoles();
}

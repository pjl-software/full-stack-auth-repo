package com.pjlsoftware.repository;

import com.pjlsoftware.authenticationConstants.RoleName;
import com.pjlsoftware.entity.Role;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseJpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

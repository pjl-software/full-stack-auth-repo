package com.pjlsoftware.repository;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.projection.AuthenticatedUserProjection;
import com.pjlsoftware.projection.GenericUserProjection;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseJpaRepository<User, Long> {
    Optional<List<GenericUserProjection>> findByEnabledIsTrue();

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT user from User user where user.username = :username and user.enabled = true ")
    Optional<AuthenticatedUserProjection> returnUserInfoForEnabledUserByUsername(String username);

    Optional<User> findByUsernameAndEnabledIsTrue(String username);
}

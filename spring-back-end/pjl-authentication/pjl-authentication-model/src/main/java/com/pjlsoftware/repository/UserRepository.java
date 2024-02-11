package com.pjlsoftware.repository;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.projection.AuthenticatedUserProjection;
import com.pjlsoftware.projection.GenericUserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<GenericUserProjection>> findByEnabledIsTrue();

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT user from User user where user.username = :username and user.enabled = true ")
    Optional<AuthenticatedUserProjection> returnUserInfoForEnabledUserByUsername(String username);

    Optional<User> findByUsernameAndEnabledIsTrue(String username);
}

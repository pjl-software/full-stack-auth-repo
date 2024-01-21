package com.pjlsoftware.repository;

import com.pjlsoftware.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<User>> findByEnabledIsTrue();

    /*
     * All valid query methods that do the same thing:
     * */
    Optional<User> findByUsername(String username);

    //    @Query(value = "SELECT user FROM User user where user.username = :username")
    //    Optional<User> findByUsername(String username);

    //    @Query(nativeQuery = true, value = "SELECT * FROM users where username = :username")
    //    Optional<User> findByUsername(String username);
}

package com.pjlsoftware.entity;

import com.pjlsoftware.authenticationConstants.RoleName;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "username"
                })
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;
    @NaturalId(mutable = true)
    private String username;
    @NotBlank
    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
        this.firstName = RandomStringUtils.randomAlphabetic(8);
        this.lastName = RandomStringUtils.randomAlphabetic(12);
        this.username = RandomStringUtils.randomAlphabetic(6);

        this.password = RandomStringUtils.randomAlphanumeric(12);

        this.enabled = true;
    }

    public User(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.enabled = true;
    }

    //
    //
    //

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //
    // Custom getters
    //
    public Set<String> getRoleNames() {
        return this.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet());
    }

    public Boolean getIsAuthenticated() {
        return true;
    }

    public Boolean getIsNotAuthenticated() {
        return !this.getIsAuthenticated();
    }

    public Boolean getIsAdmin() {
        return this.getRoleNames().contains(RoleName.ROLE_ADMIN.name());
    }

    public Boolean getIsNotAdmin() {
        return !this.getIsAdmin();
    }

    //
    //
    //

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + this.getRoleNames() +
                '}';
    }
}

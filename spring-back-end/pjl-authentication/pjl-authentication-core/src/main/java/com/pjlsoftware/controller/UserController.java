package com.pjlsoftware.controller;

import com.pjlsoftware.authenticationConstants.RoleName;
import com.pjlsoftware.entity.Role;
import com.pjlsoftware.entity.User;
import com.pjlsoftware.projection.AuthenticatedUserProjection;
import com.pjlsoftware.projection.GenericUserProjection;
import com.pjlsoftware.repository.RoleRepository;
import com.pjlsoftware.repository.UserRepository;
import com.pjlsoftware.security.CurrentUser;
import com.pjlsoftware.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(
            UserRepository userRepository
            , RoleRepository roleRepository
            , UserService userService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_FREE_USER')")
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = {"application/json"}
    )
    public ResponseEntity<String> createUser(@CurrentUser User user) {
        LOGGER.info("current user: {}", user);
        User newRandomUser = new User();
        Role freeUserRole = roleRepository.findByName(RoleName.ROLE_FREE_USER)
                .orElseThrow(() -> new RuntimeException("Couldn't find the " + RoleName.ROLE_FREE_USER +
                        " role. Are you sure you loaded the database?"));
        newRandomUser.setRoles(new HashSet<>(Set.of(freeUserRole)));

        userRepository.persist(newRandomUser);

        return new ResponseEntity<>("{\"value\": \"Created new random user\"}", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_FREE_USER')")
    @RequestMapping(
            value = "/delete",
            method = RequestMethod.PUT,
            produces = {"application/json"}
    )
    public ResponseEntity<String> deleteMe(@CurrentUser User authenticatedUser) {
        return (userService.softDeleteUser(authenticatedUser.getUsername())
                .isPresent()) ? new ResponseEntity<>("{\"value\": \"Your account has been deleted.\"}", HttpStatus.OK) :
                new ResponseEntity<>("{\"value\": \"Nothing done. Check Logs.\"}", HttpStatus.BAD_REQUEST);


    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(
            value = "/delete/{username}",
            method = RequestMethod.PUT,
            produces = {"application/json"}
    )
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        return (userService.softDeleteUser(username)
                .isPresent()) ? new ResponseEntity<>("{\"value\": \"" + username + " has been disabled.\"}", HttpStatus.OK) :
                new ResponseEntity<>("{\"value\": \"Nothing done. Check Logs.\"}", HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_FREE_USER')")
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    public ResponseEntity<List<GenericUserProjection>> getEnabledUsers() {
        return new ResponseEntity<>(userRepository.findNonGmailUsersByEnabledIsTrue()
                .orElseThrow(() -> new RuntimeException("No enabled users found.")), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_FREE_USER')")
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    public ResponseEntity<AuthenticatedUserProjection> getUserInformation(@CurrentUser User authenticatedUser) {
        LOGGER.info("authenticatedUser user: {}", authenticatedUser);
        return new ResponseEntity<>(userService.getUserInformation(authenticatedUser.getUsername())
                .orElseThrow(() -> new RuntimeException("No enabled users found.")), HttpStatus.OK);
    }
}

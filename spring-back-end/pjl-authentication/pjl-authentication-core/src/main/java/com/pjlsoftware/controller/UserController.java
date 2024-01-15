package com.pjlsoftware.controller;

import com.pjlsoftware.entity.User;
import com.pjlsoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = {"application/json"}
    )
    public ResponseEntity<String> createUser() {
        userRepository.saveAndFlush(new User());
        return new ResponseEntity<>("Created new user", HttpStatus.CREATED);
    }
}

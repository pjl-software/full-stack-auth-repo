package com.pjlsoftware.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApplicationCoreController {
    @GetMapping("health")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("{\"status\":\"UP\"}", HttpStatus.OK);
    }
}

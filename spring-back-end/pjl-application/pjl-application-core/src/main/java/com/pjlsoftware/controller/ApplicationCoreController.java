package com.pjlsoftware.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApplicationCoreController {
    @RequestMapping(
            value = "/health",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("{\"status\":\"UP\"}", HttpStatus.OK);
    }
}

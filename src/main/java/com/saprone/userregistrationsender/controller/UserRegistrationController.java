package com.saprone.userregistrationsender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saprone.userregistrationsender.model.UserRegistration;
import com.saprone.userregistrationsender.service.UserRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class UserRegistrationController {

    private final UserRegistrationService registrationService;

    public UserRegistrationController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistration userRegistration) throws JsonProcessingException {
        registrationService.registerUser(userRegistration);
        return ResponseEntity.accepted().build();
    }
}

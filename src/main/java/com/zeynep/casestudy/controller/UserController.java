package com.zeynep.casestudy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    @GetMapping("/user")
    public String getUser(Authentication authentication) {
        return "Hello Spring Security: " + authentication.getName();
    }
}

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

    //source: https://springjavatutorial.medium.com/spring-security-in-memory-authentication-in-spring-boot-a1870db57059

}

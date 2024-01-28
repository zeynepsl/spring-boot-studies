package com.zeynep.casestudy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/msg/v1")
class UserController {

    /*
    we create a simple REST API and protect it using Spring Security.
    Well, if we add Spring security dependency to the Spring boot project then by default Spring Security secures all the application URLs. */

    @GetMapping
    public String greetings() {
        return "Hello world!";
    }

    @GetMapping("/user")
    public String getUser(Authentication authentication) {
        return "Hello Spring Security User: " + authentication.getName();
    }

    @GetMapping("/admin")
    public String getAdmin(Authentication authentication) {
        return "Hello Spring Security admin: " + authentication.getName();
    }

    @GetMapping("/hello")
    public String getAll() {
        return "Hello Spring Security other users";
    }

    //acknowledgements:
    // https://springjavatutorial.medium.com/spring-security-in-memory-authentication-in-spring-boot-a1870db57059
    // https://www.javaguides.net/2023/04/spring-security-tutorial-in-memory-authentication.html
    // https://medium.com/@ritesh.panigrahi/spring-security-in-memory-authentication-and-authorization-dcb9cc8baf19
    // https://www.geeksforgeeks.org/spring-boot-3-0-jwt-authentication-with-spring-security-using-mysql-database/
    // https://github.com/YouTubeProjectsCode/Spring_Secuirty-Angular_Authentication-CodeElevate/tree/main/Spring-Secuirty-Jwt-In-Spring-Boot-3


}

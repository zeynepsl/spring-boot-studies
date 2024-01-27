package com.zeynep.casestudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class LoginController {
    @GetMapping("/login")
    String login() {
        return "login";
    }
}

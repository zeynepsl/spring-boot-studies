package com.zeynep.casestudy.controller;

import com.zeynep.casestudy.aspect.Timed;
import com.zeynep.casestudy.model.JwtAuthenticationResponse;
import com.zeynep.casestudy.model.SignUpRequest;
import com.zeynep.casestudy.model.SigninRequest;
import com.zeynep.casestudy.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;

    @Timed
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) throws InterruptedException {
        Thread.sleep(1000);
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @Timed
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) throws InterruptedException {
        Thread.sleep(1000);
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}

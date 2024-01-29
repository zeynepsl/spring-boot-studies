package com.zeynep.casestudy.controller;

import com.zeynep.casestudy.model.request.SignUpRequest;
import com.zeynep.casestudy.model.request.SigninRequest;
import com.zeynep.casestudy.model.response.JwtAuthenticationResponse;
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

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    /*@PostMapping("/login")
    public ResponseEntity<Map<String, String>> authRequest(@RequestBody AuthRequestDto authRequestDto) {
        log.info("AuthResource.authRequest start {}", authRequestDto);
        var userRegistrationResponse = authService.authRequest(authRequestDto);
        log.info("AuthResource.authRequest end {}", userRegistrationResponse);
        return new ResponseEntity<>(userRegistrationResponse, HttpStatus.OK);
    }*/
}

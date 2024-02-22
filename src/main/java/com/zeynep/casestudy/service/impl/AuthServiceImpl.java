package com.zeynep.casestudy.service.impl;

import com.zeynep.casestudy.model.JwtAuthenticationResponse;
import com.zeynep.casestudy.model.SignUpRequest;
import com.zeynep.casestudy.model.SigninRequest;
import com.zeynep.casestudy.model.entity.Role;
import com.zeynep.casestudy.model.entity.User;
import com.zeynep.casestudy.repository.UserRepository;
import com.zeynep.casestudy.service.AuthService;
import com.zeynep.casestudy.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        //The process begins when a user sends a sign-in request to the Service
        //An Authentication object called UsernamePasswordAuthenticationToken is then generated, using the provided username and password:
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        /*The AuthenticationManager is responsible for authenticating the Authentication object,
        handling all necessary tasks. If the username or password is incorrect,
        an exception is thrown, and a response with HTTP Status 403 is returned to the user.

        UsernamePasswordAuthenticationToken --> A type of Authentication object which can be created from a username and password that are submitted.
        AuthenticationManager: Processes authentication object and will do all authentication jobs for us. */
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /*
    @Override
    public Map<String, String> authRequest(AuthRequestDto authRequestDto) {
        final var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.username(), authRequestDto.password()));

        final var username = userDetails.getUsername();
        final var token = jwtService.generateToken(Map.of("role", roles), username);
        return Map.of("token", token);
    }
    }*/
}

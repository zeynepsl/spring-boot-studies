package com.zeynep.casestudy.service;

import com.zeynep.casestudy.model.entity.Role;
import com.zeynep.casestudy.model.entity.User;
import com.zeynep.casestudy.model.request.SignUpRequest;
import com.zeynep.casestudy.model.request.SigninRequest;
import com.zeynep.casestudy.model.response.JwtAuthenticationResponse;
import com.zeynep.casestudy.repository.UserRepository;
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


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    /*
    @Override
    public Map<String, String> authRequest(AuthRequestDto authRequestDto) {
        final var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.username(), authRequestDto.password()));
        final var userDetails = (UserDetails) authenticate.getPrincipal();
        return getToken(userDetails);
    }

    public Map<String, String> getToken(UserDetails userDetails) {
        final var roles = userDetails.getAuthorities();
        final var username = userDetails.getUsername();
        final var token = jwtService.generateToken(Map.of("role", roles), username);
        return Map.of("token", token);
    }*/
}

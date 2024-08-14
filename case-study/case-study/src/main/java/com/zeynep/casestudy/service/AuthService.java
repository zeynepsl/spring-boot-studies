package com.zeynep.casestudy.service;

import com.zeynep.casestudy.model.JwtAuthenticationResponse;
import com.zeynep.casestudy.model.SignUpRequest;
import com.zeynep.casestudy.model.SigninRequest;

public interface AuthService {

    //Map<String, String> authRequest(AuthRequestDto authRequestDto);

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}

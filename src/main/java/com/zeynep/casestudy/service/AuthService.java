package com.zeynep.casestudy.service;

import com.zeynep.casestudy.model.request.SignUpRequest;
import com.zeynep.casestudy.model.request.SigninRequest;
import com.zeynep.casestudy.model.response.JwtAuthenticationResponse;

public interface AuthService {

    //Map<String, String> authRequest(AuthRequestDto authRequestDto);

    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

}

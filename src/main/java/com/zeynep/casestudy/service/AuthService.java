package com.zeynep.casestudy.service;

import com.zeynep.casestudy.model.AuthRequestDto;

import java.util.Map;

public interface AuthService {

    Map<String, String> authRequest(AuthRequestDto authRequestDto);

}

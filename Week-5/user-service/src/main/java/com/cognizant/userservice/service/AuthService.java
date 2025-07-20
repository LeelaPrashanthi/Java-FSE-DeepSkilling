package com.cognizant.userservice.service;

import com.cognizant.userservice.dto.AuthResponseDto;
import com.cognizant.userservice.dto.LoginRequestDto;
import com.cognizant.userservice.dto.UserRegistrationDto;

public interface AuthService {
    
    AuthResponseDto login(LoginRequestDto loginRequest);
    
    AuthResponseDto register(UserRegistrationDto registrationDto);
} 
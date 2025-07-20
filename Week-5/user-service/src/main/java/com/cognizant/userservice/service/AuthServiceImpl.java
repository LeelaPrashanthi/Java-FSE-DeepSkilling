package com.cognizant.userservice.service;

import com.cognizant.userservice.dto.AuthResponseDto;
import com.cognizant.userservice.dto.LoginRequestDto;
import com.cognizant.userservice.dto.UserRegistrationDto;
import com.cognizant.userservice.dto.UserResponseDto;
import com.cognizant.userservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }
    
    @Override
    public AuthResponseDto login(LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        UserResponseDto user = userService.getUserByUsername(loginRequest.getUsername());
        return new AuthResponseDto(jwt, user);
    }
    
    @Override
    public AuthResponseDto register(UserRegistrationDto registrationDto) {
        UserResponseDto user = userService.registerUser(registrationDto);
        String jwt = tokenProvider.generateToken(user.getUsername());
        return new AuthResponseDto(jwt, user);
    }
} 
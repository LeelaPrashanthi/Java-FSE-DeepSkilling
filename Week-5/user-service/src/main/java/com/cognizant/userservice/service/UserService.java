package com.cognizant.userservice.service;

import com.cognizant.userservice.dto.UserRegistrationDto;
import com.cognizant.userservice.dto.UserResponseDto;
import com.cognizant.userservice.entity.User;

import java.util.List;

public interface UserService {
    
    UserResponseDto registerUser(UserRegistrationDto registrationDto);
    
    UserResponseDto getUserById(Long id);
    
    UserResponseDto getUserByUsername(String username);
    
    List<UserResponseDto> getAllUsers();
    
    UserResponseDto updateUser(Long id, UserRegistrationDto updateDto);
    
    void deleteUser(Long id);
    
    void deactivateUser(Long id);
    
    void activateUser(Long id);
    
    User findByUsername(String username);
} 
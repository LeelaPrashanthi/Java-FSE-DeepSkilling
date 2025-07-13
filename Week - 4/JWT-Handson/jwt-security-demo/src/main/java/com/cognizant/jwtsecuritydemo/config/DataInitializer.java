package com.cognizant.jwtsecuritydemo.config;

import com.cognizant.jwtsecuritydemo.entity.User;
import com.cognizant.jwtsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create admin user
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            
            Set<String> adminRoles = new HashSet<>();
            adminRoles.add("ADMIN");
            admin.setRoles(adminRoles);
            
            userRepository.save(admin);
            System.out.println("Admin user created: admin/admin123");
        }
        
        // Create moderator user
        if (!userRepository.existsByUsername("moderator")) {
            User moderator = new User();
            moderator.setUsername("moderator");
            moderator.setEmail("moderator@example.com");
            moderator.setPassword(passwordEncoder.encode("mod123"));
            
            Set<String> modRoles = new HashSet<>();
            modRoles.add("MODERATOR");
            moderator.setRoles(modRoles);
            
            userRepository.save(moderator);
            System.out.println("Moderator user created: moderator/mod123");
        }
        
        // Create regular user
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            
            Set<String> userRoles = new HashSet<>();
            userRoles.add("USER");
            user.setRoles(userRoles);
            
            userRepository.save(user);
            System.out.println("Regular user created: user/user123");
        }
    }
} 
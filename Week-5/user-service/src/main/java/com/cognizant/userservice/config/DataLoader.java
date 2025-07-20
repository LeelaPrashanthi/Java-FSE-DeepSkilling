package com.cognizant.userservice.config;

import com.cognizant.userservice.entity.User;
import com.cognizant.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@cognizant.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setRole(User.Role.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
            System.out.println("Admin user created successfully");
        }
        
        // Create sample users if not exists
        if (!userRepository.existsByUsername("john.doe")) {
            User user1 = new User();
            user1.setUsername("john.doe");
            user1.setEmail("john.doe@example.com");
            user1.setPassword(passwordEncoder.encode("password123"));
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setPhoneNumber("+1234567890");
            user1.setRole(User.Role.USER);
            user1.setActive(true);
            userRepository.save(user1);
            System.out.println("Sample user John Doe created successfully");
        }
        
        if (!userRepository.existsByUsername("jane.smith")) {
            User user2 = new User();
            user2.setUsername("jane.smith");
            user2.setEmail("jane.smith@example.com");
            user2.setPassword(passwordEncoder.encode("password123"));
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setPhoneNumber("+0987654321");
            user2.setRole(User.Role.MODERATOR);
            user2.setActive(true);
            userRepository.save(user2);
            System.out.println("Sample user Jane Smith created successfully");
        }
    }
} 
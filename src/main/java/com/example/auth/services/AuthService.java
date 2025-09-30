package com.example.auth.services;

import com.example.auth.entities.User;
import com.example.auth.repositories.UserRepository;
import com.example.auth.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user.getEmail());
    }

    public String register(String email){
        return jwtService.generateToken(email);
    }
}

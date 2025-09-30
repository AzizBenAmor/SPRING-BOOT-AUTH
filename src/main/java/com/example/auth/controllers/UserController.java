package com.example.auth.controllers;

import com.example.auth.Dtos.LoginUserRequest;
import com.example.auth.Dtos.RegisterUserRequest;
import com.example.auth.Dtos.UserDto;
import com.example.auth.entities.User;
import com.example.auth.mappers.UserMapper;
import com.example.auth.repositories.UserRepository;
import com.example.auth.security.JwtService;
import com.example.auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder ;
    private AuthService authService;
    private JwtService jwtService;
    @PostMapping("register")
    public String Register(@RequestBody RegisterUserRequest request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userMapper.toEntity(request);
        userRepository.save(user);
        return authService.register(user.getEmail());
    }

    @PostMapping("login")
    public String login(@RequestBody LoginUserRequest request){
        return authService.login(request.getEmail(),request.getPassword());
    }

    @GetMapping("me")
    public UserDto me(@AuthenticationPrincipal UserDetails userDetails) {
        return userMapper.toDto(
                userRepository.findFirstByEmail(userDetails.getUsername())
                        .orElseThrow()
        );
    }

}

package com.example.auth.Dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String name;
    private String password;
    private String email;
    private String phone;
}

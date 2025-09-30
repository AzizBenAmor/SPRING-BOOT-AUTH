package com.example.auth.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
}

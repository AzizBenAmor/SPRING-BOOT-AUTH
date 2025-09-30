package com.example.auth.mappers;

import com.example.auth.Dtos.RegisterUserRequest;
import com.example.auth.Dtos.UserDto;
import com.example.auth.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface UserMapper{
    UserDto toDto(User user);
    @Mapping(target = "id", ignore = true)
    User toEntity(RegisterUserRequest request);
}

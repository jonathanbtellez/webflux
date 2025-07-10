package com.mycode.ecommerce.handler.mapper;

import com.mycode.ecommerce.handler.dto.RegisterDto;
import com.mycode.ecommerce.service.model.User;

public class RegisterMapper {
    public static User fromRegisterDtoToUser(RegisterDto registerDto) {
        return User.builder()
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(registerDto.getPassword())
                .build();
    }
}

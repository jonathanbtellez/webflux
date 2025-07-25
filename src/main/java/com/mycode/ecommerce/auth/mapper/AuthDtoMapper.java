package com.mycode.ecommerce.auth.mapper;

import com.mycode.ecommerce.auth.dto.AuthDto;
import com.mycode.ecommerce.auth.service.model.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthDtoMapper {
    AuthDto fromAuthToAuthDto(Auth dto);
}

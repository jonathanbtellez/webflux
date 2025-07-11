package com.mycode.ecommerce.auth.mapper;

import com.mycode.ecommerce.auth.dto.RegisterDto;
import com.mycode.ecommerce.service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    User fromRegisterDtoToUser(RegisterDto dto);
}

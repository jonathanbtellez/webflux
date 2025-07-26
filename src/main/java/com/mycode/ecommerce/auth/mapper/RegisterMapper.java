package com.mycode.ecommerce.auth.mapper;

import com.mycode.ecommerce.auth.dto.RegisterDto;
import com.mycode.ecommerce.auth.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "lastLoginAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "failedAttempts", constant = "0")
    @Mapping(target = "lockedUntil", expression = "java(null)")
    User fromRegisterDtoToUser(RegisterDto dto);
}

package com.mycode.ecommerce.auth.service.mapper;

import com.mycode.ecommerce.auth.repository.entity.UserEntity;
import com.mycode.ecommerce.auth.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "isActive", constant = "true")
    UserEntity fromUserToUserEntity(User user);

    @Mapping(target = "isActive", constant = "true")
    User fromUserEntityToUser(UserEntity user);
}

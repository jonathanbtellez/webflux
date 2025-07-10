package com.mycode.ecommerce.service.mapper;

import com.mycode.ecommerce.repository.entity.UserEntity;
import com.mycode.ecommerce.service.model.User;

import java.util.UUID;

public class UserMapper {
    public static UserEntity fromUserToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId() == null ? UUID.randomUUID() : user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User fromUserEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }
}

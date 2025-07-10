package com.mycode.ecommerce.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Builder
@Getter
@Setter
public class UserEntity {
    @Id
    private UUID id;
    private String username;
    private String email;
    private String password;
    private boolean isActive;
    private int failedAttempts;
    private LocalDateTime lastLoginAt;
    private LocalDateTime lockedUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

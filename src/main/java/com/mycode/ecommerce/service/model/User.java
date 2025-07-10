package com.mycode.ecommerce.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class User {
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

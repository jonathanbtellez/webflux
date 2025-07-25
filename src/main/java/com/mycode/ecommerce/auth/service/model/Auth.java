package com.mycode.ecommerce.auth.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Auth {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private String tokenType;
    private UUID userId;
}

package com.mycode.ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class AuthDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
    private UUID userId;
}

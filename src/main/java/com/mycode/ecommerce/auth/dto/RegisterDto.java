package com.mycode.ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterDto {
    private String username;
    private String email;
    private String password;
}

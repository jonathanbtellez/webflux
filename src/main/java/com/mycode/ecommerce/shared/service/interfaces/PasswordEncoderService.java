package com.mycode.ecommerce.shared.service.interfaces;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderService {
    String hash(String raw);
    boolean matches(String raw, String hash);
}

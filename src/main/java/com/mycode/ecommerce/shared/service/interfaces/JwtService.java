package com.mycode.ecommerce.shared.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token);
    public String extractUsername(String token);
    public List<String> extractRoles(String token);
}

package com.mycode.ecommerce.shared.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {
    public String extractUsername(String token);
    public List<String> extractRoles(String token);
    public String generateToken(UserDetails userDetails);
    public String generateRefreshToken(UserDetails userDetails);
    public Long getExpirationMs();
    public String getTokenType();
    public boolean isTokenValid(String token);
}

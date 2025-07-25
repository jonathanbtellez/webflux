package com.mycode.ecommerce.shared.service.implement;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mycode.ecommerce.shared.service.interfaces.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    @Value("${jwt.expirationRefresh}")
    private long expirationRefreshMs;

    @Value("${jwt.tokenType}")
    private String tokenType;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(UserDetails user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
                .sign(algorithm);
    }

    public String generateRefreshToken(UserDetails user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationRefreshMs))
                .sign(algorithm);
    }

    @Override
    public Long getExpirationMs() {
        return expirationMs;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    public boolean isTokenValid(String token) {
        try { JWT.require(algorithm).build().verify(token); return true; }
        catch (JWTVerificationException e) { return false; }
    }

    public String extractUsername(String token) {
        return JWT.require(algorithm).build().verify(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return JWT.require(algorithm).build().verify(token)
                .getClaim("roles").asList(String.class);
    }
}

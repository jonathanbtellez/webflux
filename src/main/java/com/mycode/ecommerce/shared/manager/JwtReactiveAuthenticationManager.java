package com.mycode.ecommerce.shared.manager;

import com.mycode.ecommerce.shared.service.interfaces.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication auth) {
        return Mono.just(auth)
                .cast(UsernamePasswordAuthenticationToken.class)
                .filter(token -> jwtService.isTokenValid((String) token.getCredentials()))
                .map(token -> {
                    token.setAuthenticated(true);
                    return token;
                });
    }
}

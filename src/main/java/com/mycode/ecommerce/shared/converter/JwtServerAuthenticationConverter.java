package com.mycode.ecommerce.shared.converter;

import com.mycode.ecommerce.shared.service.interfaces.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {
    private static final String BEARER = "Bearer ";
    private final JwtService jwtService;

    @Override
    public Mono<Authentication> convert(ServerWebExchange ex) {
        return Mono.justOrEmpty(ex.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(h -> h.startsWith(BEARER))
                .map(h -> h.substring(BEARER.length()))
                .filter(jwtService::isTokenValid)
                .map(token -> {
                    String username = jwtService.extractUsername(token);
                    List<SimpleGrantedAuthority> auths = jwtService.extractRoles(token).stream()
                            .map(SimpleGrantedAuthority::new).toList();
                    return new UsernamePasswordAuthenticationToken(username, token, auths);
                });
    }
}

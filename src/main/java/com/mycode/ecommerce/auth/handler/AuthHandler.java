package com.mycode.ecommerce.auth.handler;

import com.mycode.ecommerce.auth.dto.RegisterDto;
import com.mycode.ecommerce.auth.mapper.RegisterMapper;
import com.mycode.ecommerce.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuthHandler {
    private final RegisterMapper registerMapper;
    private final AuthService authService;

    public Mono<ServerResponse> register(ServerRequest request) {
        log.info("Register called");
        return request.bodyToMono(RegisterDto.class)
                .map(registerMapper::fromRegisterDtoToUser)
                .flatMap(authService::register)
                .flatMap(savedProfile -> ServerResponse.ok().bodyValue(savedProfile));
    }
}

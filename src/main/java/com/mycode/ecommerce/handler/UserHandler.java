package com.mycode.ecommerce.handler;

import com.mycode.ecommerce.handler.dto.RegisterDto;
import com.mycode.ecommerce.handler.mapper.RegisterMapper;
import com.mycode.ecommerce.repository.interfaces.UserRepository;
import com.mycode.ecommerce.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserHandler {

    private final AuthService authService;

    public Mono<ServerResponse> register(ServerRequest request) {
        Mono<RegisterDto> registerMono = request.bodyToMono(RegisterDto.class);

        return registerMono.map(registerDto -> authService.register(RegisterMapper.fromRegisterDtoToUser(registerDto))
                .map(savedProfile -> ServerResponse.ok().bodyValue(savedProfile));
    }
}

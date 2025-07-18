package com.mycode.ecommerce.auth.router;

import com.mycode.ecommerce.auth.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AuthRouter {

    private final AuthHandler authHandler;

    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        log.info("Config auth routes");
        return RouterFunctions.route()
                .nest(path("/auth"), builder ->
                    builder.route(POST("/register"), authHandler::register)
                            .route(POST("/login"), authHandler::login)
                ).build();
    }


}

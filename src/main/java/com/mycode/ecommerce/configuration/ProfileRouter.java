package com.mycode.ecommerce.configuration;

import com.mycode.ecommerce.handler.ProfileHandler;
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
public class ProfileRouter {

    private final ProfileHandler profileHandler;

    @Bean
    public RouterFunction<ServerResponse> profileRoutes() {
        log.info("Loading profile routes");
        return RouterFunctions.route(GET("/profile"), profileHandler::getAll)
                .andRoute(GET("/profile/{id}"), profileHandler::getUserBy);
    }


}

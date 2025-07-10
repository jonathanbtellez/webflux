package com.mycode.ecommerce.handler;

import com.mycode.ecommerce.service.model.Profile;
import com.mycode.ecommerce.service.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileHandler {

    private final ProfileService profileService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
         return ServerResponse.ok().body(profileService.all(), Profile.class);
    }

    public Mono<ServerResponse> getUserBy(ServerRequest request) {
        String userId = request.pathVariable("id");
        log.info("Getting user by id {}", userId);
        Mono<Profile> profileMono = profileService.getById(userId);
        return profileMono
                .flatMap(
                        profile -> {
                            log.info("Profile: {}", profile);
                            return ServerResponse.ok().bodyValue(profile);
                        })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Profile> profileMono = request.bodyToMono(Profile.class);

        return profileMono.flatMap(profileService::save)
                .flatMap(savedProfile -> ServerResponse.ok().bodyValue(savedProfile));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<Profile> profileMono = request.bodyToMono(Profile.class);
        return profileMono.flatMap(profileService::update)
                .flatMap(savedProfile -> ServerResponse.ok().bodyValue(savedProfile));
    }
}

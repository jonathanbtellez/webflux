package com.mycode.ecommerce.handler;

import com.mycode.ecommerce.model.Profile;
import com.mycode.ecommerce.repository.ProfileRepository;
import com.mycode.ecommerce.service.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileHandler {

    private final ProfileService profileService;

    @GetMapping
    public Mono<ServerResponse> getAll(ServerRequest request) {
         return ServerResponse.ok().body(profileService.all(), Profile.class);
    }


    public Mono<ServerResponse> getUserBy(ServerRequest request) {
        String userId = request.pathVariable("id");
        log.info("Getting user by id {}", userId);
        Mono<Profile> profileMono = profileService.getById(userId);
        log.info("profileMono {}", profileMono);
        return profileMono
                .flatMap(
                        profile -> {
                            log.info("Profile: {}", profile);
                            return ServerResponse.ok().bodyValue(profile);
                        })
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}

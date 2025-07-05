package com.mycode.ecommerce.factory;

import com.mycode.ecommerce.model.Profile;
import com.mycode.ecommerce.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileFactory implements ApplicationListener<ApplicationReadyEvent> {

    private final ProfileRepository profileRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        profileRepository
                .deleteAll()
                .thenMany(
                        Flux.just("A","B","C","D")
                        .map(name ->
                                Profile.builder()
                                        .id(UUID.randomUUID().toString())
                                        .name(name + "@mail.com")
                                        .build()
                        )
                        .flatMap(profileRepository::save)
                ).thenMany(profileRepository.findAll())
                .subscribe(profile -> log.info("Saving: {}",  profile.toString()));
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

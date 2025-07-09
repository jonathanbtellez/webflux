package com.mycode.ecommerce.service;

import com.mycode.ecommerce.model.Profile;
import com.mycode.ecommerce.repository.ProfileRepository;
import com.mycode.ecommerce.service.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Flux<Profile> all() {
        return profileRepository.findAll();
    }

    @Override
    public Mono<Profile> getById(String id) {
        return profileRepository.findById(String.valueOf(id));
    }

    @Override
    public Mono<Profile> save(Profile profile) {
        return profileRepository.save(Profile
                .builder()
                        .id(UUID.randomUUID())
                        .name(profile.getName())
                .build()
        );
    }

    @Override
    public Mono<Profile> update(Profile profile) {
        return profileRepository.save(profile);
    }
}

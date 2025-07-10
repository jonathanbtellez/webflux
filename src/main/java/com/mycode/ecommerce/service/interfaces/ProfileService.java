package com.mycode.ecommerce.service.interfaces;

import com.mycode.ecommerce.service.model.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProfileService {
    Flux<Profile> all();
    Mono<Profile> getById(String id);
    Mono<Profile> save(Profile profile);
    Mono<Profile> update(Profile profile);
}

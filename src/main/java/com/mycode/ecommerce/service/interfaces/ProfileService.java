package com.mycode.ecommerce.service.interfaces;

import com.mycode.ecommerce.model.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProfileService {
    Flux<Profile> all();
    Mono<Profile> getById(String id);
}

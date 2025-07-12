package com.mycode.ecommerce.auth.repository.interfaces;

import com.mycode.ecommerce.auth.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
    Mono<UserEntity> findByUsernameOrEmail(String username, String email);
}

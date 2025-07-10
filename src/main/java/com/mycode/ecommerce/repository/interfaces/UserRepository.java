package com.mycode.ecommerce.repository.interfaces;

import com.mycode.ecommerce.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {
}

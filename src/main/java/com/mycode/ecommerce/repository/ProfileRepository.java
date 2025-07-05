package com.mycode.ecommerce.repository;

import com.mycode.ecommerce.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends ReactiveMongoRepository<Profile, String> {
}

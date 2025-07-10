package com.mycode.ecommerce.repository.interfaces;

import com.mycode.ecommerce.service.model.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository  extends ReactiveMongoRepository<Profile, String> {
}

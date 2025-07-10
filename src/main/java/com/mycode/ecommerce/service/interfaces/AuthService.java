package com.mycode.ecommerce.service.interfaces;

import com.mycode.ecommerce.service.model.User;
import reactor.core.publisher.Mono;

public interface AuthService {
   Mono<User> register(User user);
}

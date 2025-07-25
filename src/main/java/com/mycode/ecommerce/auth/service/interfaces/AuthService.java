package com.mycode.ecommerce.auth.service.interfaces;

import com.mycode.ecommerce.auth.service.model.Auth;
import com.mycode.ecommerce.auth.service.model.User;
import reactor.core.publisher.Mono;

public interface AuthService {
   Mono<User> register(User user);
   Mono<Auth> login(User user);
}

package com.mycode.ecommerce.service.implement;

import com.mycode.ecommerce.repository.interfaces.UserRepository;
import com.mycode.ecommerce.service.interfaces.AuthService;
import com.mycode.ecommerce.service.mapper.UserMapper;
import com.mycode.ecommerce.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public Mono<User> register(User user) {
        return userRepository.save(UserMapper.fromUserToUserEntity(user)).map(UserMapper::fromUserEntityToUser);
    }
}

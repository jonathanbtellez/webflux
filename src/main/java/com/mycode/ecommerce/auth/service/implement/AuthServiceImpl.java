package com.mycode.ecommerce.auth.service.implement;

import com.mycode.ecommerce.auth.repository.entity.UserEntity;
import com.mycode.ecommerce.auth.repository.interfaces.UserRepository;
import com.mycode.ecommerce.auth.service.interfaces.AuthService;
import com.mycode.ecommerce.auth.service.mapper.UserMapper;
import com.mycode.ecommerce.auth.service.model.User;
import com.mycode.ecommerce.shared.exception.DuplicateUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> register(User user) {
        return userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .flatMap(u -> Mono.<UserEntity>error(new DuplicateUserException("User already exists")))
                .switchIfEmpty(
                        Mono.defer(() ->
                                userRepository.save(userMapper.fromUserToUserEntity(user))
                        )
                )
                .map(userMapper::fromUserEntityToUser);
    }
}

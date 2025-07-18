package com.mycode.ecommerce.auth.service.implement;

import com.mycode.ecommerce.auth.repository.entity.UserEntity;
import com.mycode.ecommerce.auth.repository.interfaces.UserRepository;
import com.mycode.ecommerce.auth.service.interfaces.AuthService;
import com.mycode.ecommerce.auth.service.mapper.UserMapper;
import com.mycode.ecommerce.auth.service.model.CustomUserDetails;
import com.mycode.ecommerce.auth.service.model.User;
import com.mycode.ecommerce.shared.exception.DuplicateUserException;
import com.mycode.ecommerce.shared.service.interfaces.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

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

    @Override
    public Mono<User> login(User user) {
        return userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .flatMap(userEntity -> {
                    User userInDb = userMapper.fromUserEntityToUser(userEntity);
                    log.info(userInDb.toString());
                    return Mono.just(userInDb);
                }).switchIfEmpty( Mono.defer(() -> Mono.error(new DuplicateUserException("User not found"))))
                .map(userDb -> {
                    String token = jwtService.generateToken(new CustomUserDetails(userDb));
                    log.info(token);
                    return userDb;
                });
    }
}

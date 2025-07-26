package com.mycode.ecommerce.auth.service.implement;

import com.mycode.ecommerce.auth.repository.entity.UserEntity;
import com.mycode.ecommerce.auth.repository.interfaces.UserRepository;
import com.mycode.ecommerce.auth.service.interfaces.AuthService;
import com.mycode.ecommerce.auth.service.mapper.UserMapper;
import com.mycode.ecommerce.auth.service.model.Auth;
import com.mycode.ecommerce.auth.service.model.CustomUserDetails;
import com.mycode.ecommerce.auth.service.model.User;
import com.mycode.ecommerce.shared.exception.DuplicateUserException;
import com.mycode.ecommerce.shared.exception.NotFoundException;
import com.mycode.ecommerce.shared.exception.UnauthorizedException;
import com.mycode.ecommerce.shared.service.interfaces.JwtService;
import com.mycode.ecommerce.shared.service.interfaces.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoderService passwordEncoderService;

    @Override
    public Mono<User> register(User user) {
        return userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .flatMap(u -> Mono.<UserEntity>error(new DuplicateUserException("User already exists")))
                .switchIfEmpty(
                        Mono.defer(() -> {
                            user.setPassword(passwordEncoderService.hash(user.getPassword()));
                            return userRepository.save(userMapper.fromUserToUserEntity(user));
                        })
                )
                .map(userMapper::fromUserEntityToUser);
    }

    @Override
    public Mono<Auth> login(User user) {
        return userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .flatMap(userEntity -> {
                    User userInDb = userMapper.fromUserEntityToUser(userEntity);
                    log.info(userInDb.toString());
                    return Mono.just(userInDb);
                }).switchIfEmpty( Mono.defer(() -> Mono.error(new NotFoundException("User not found"))))
                .flatMap(userDb -> {

                    boolean wrongPwd = !passwordEncoderService.matches(user.getPassword(), userDb.getPassword());

                    if (wrongPwd) {
                        userDb.setFailedAttempts(userDb.getFailedAttempts() + 1);
                        return userRepository.save(userMapper.fromUserToUserEntity(userDb))
                                .then(Mono.error(new UnauthorizedException("Invalid username or password")));
                    }

                    Auth auth = Auth.builder()
                            .accessToken(jwtService.generateToken(new CustomUserDetails(userDb)))
                            .refreshToken(jwtService.generateRefreshToken(new CustomUserDetails(userDb)))
                            .userId(userDb.getId())
                            .tokenType(jwtService.getTokenType())
                            .expiresIn(jwtService.getExpirationMs())
                            .build();
                    return Mono.just(auth);
                });
    }
}

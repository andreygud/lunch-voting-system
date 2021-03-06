package com.gudilov.lunchvotingsystem.user.mock;

import com.gudilov.lunchvotingsystem.user.repository.UserRepository;
import com.gudilov.lunchvotingsystem.user.service.UserService;
import com.gudilov.lunchvotingsystem.user.service.mapper.UserMapper;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MockUserService extends UserService {

    public MockUserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userMapper, userRepository, passwordEncoder);
    }

    @Override
    public UserViewTo get(int id) {
        throw new RuntimeException("Cannot process the request");
    }
}

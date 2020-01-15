package com.gudilov.lunchvotingsystem.user.service;

import com.gudilov.lunchvotingsystem.user.model.User;
import com.gudilov.lunchvotingsystem.user.repository.UserRepository;
import com.gudilov.lunchvotingsystem.user.security.AuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername username={}",username);
        User user = userRepository.getByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        return new AuthorizedUser(user);
    }
}

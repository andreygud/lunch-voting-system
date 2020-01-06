package com.gudilov.lunchvotingsystem.user.service;

import com.gudilov.lunchvotingsystem.user.model.User;
import com.gudilov.lunchvotingsystem.user.repository.UserRepository;
import com.gudilov.lunchvotingsystem.user.service.mapper.UserMapper;
import com.gudilov.lunchvotingsystem.user.to.UserCreateTo;
import com.gudilov.lunchvotingsystem.user.to.UserUpdateTo;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.checkNew;
import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.checkNotFoundWithId;
import static org.springframework.util.Assert.notNull;

@Service
public class UserService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserViewTo create(UserCreateTo createTo) {
        log.debug("create {}", createTo);
        notNull(createTo, "user must not be null");
        checkNew(createTo);
        User user = userRepository.save(userMapper.transformToIntoEntity(createTo));
        return userMapper.transformEntityIntoViewTo(user);
    }

    @Transactional
    public void update(UserUpdateTo updateTo) {
        log.debug("update {}", updateTo);
        notNull(updateTo, "user must not be null");

        User user = checkNotFoundWithId(userRepository.get(updateTo.getId()), updateTo.getId());
        userMapper.updateExistingEntity(updateTo,user);
    }


    public void delete(int id) {
        log.debug("delete {}", id);
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public UserViewTo get(int id) {
        log.debug("get {}", id);
        User user = checkNotFoundWithId(userRepository.get(id), id);
        return userMapper.transformEntityIntoViewTo(user);
    }

    public List<UserViewTo> getAll() {
        log.debug("getAll");
        List<User> users = userRepository.getAll();
        return userMapper.transformEntitiesIntoViewTos(users);
    }

}

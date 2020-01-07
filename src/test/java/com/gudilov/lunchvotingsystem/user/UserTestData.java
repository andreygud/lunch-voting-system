package com.gudilov.lunchvotingsystem.user;

import com.gudilov.lunchvotingsystem.common.TestMatchers;
import com.gudilov.lunchvotingsystem.user.model.Role;
import com.gudilov.lunchvotingsystem.user.model.User;
import com.gudilov.lunchvotingsystem.user.service.mapper.UserMapper;
import com.gudilov.lunchvotingsystem.user.to.UserCreateTo;
import com.gudilov.lunchvotingsystem.user.to.UserUpdateTo;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;

import java.time.LocalDateTime;
import java.util.Set;

public class UserTestData {
    public static final int USER_ID = 100000;
    public static final User USER = new User(USER_ID,"User","user@yandex.ru","{noop}password",true, LocalDateTime.now(),Set.of(Role.ROLE_USER));
    public static final UserViewTo USER_VIEW_TO = UserMapper.INSTANCE.transformEntityIntoViewTo(USER);
    public static final int ADMIN_ID = 100001;
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "{noop}admin", true, LocalDateTime.now(), Set.of(Role.ROLE_ADMIN, Role.ROLE_USER));
    public static final UserViewTo ADMIN_VIEW_TO = UserMapper.INSTANCE.transformEntityIntoViewTo(ADMIN);

    public static final int USER1_ID = 100005;
    public static final UserCreateTo USER_1_CTO = new UserCreateTo(null, "Sam", "sam@gmail.com", "password", null);
    public static final UserViewTo USER1_VIEW_TO = new UserViewTo(USER1_ID, "Sam", "sam@gmail.com", true, null, Set.of(Role.ROLE_USER));

    public static final UserCreateTo USER_DUPLICATE_CTO = new UserCreateTo(null, "Sam", "user@yandex.ru", "password", null);
    public static final Set<String> VIOLATED_VALIDATIONS_CTO = Set.of("must not be blank", "must be a well-formed email address", "size must be between 2 and 100");
    public static final UserCreateTo WRONG_ALL_INPUT_USER_CTO = new UserCreateTo(null, "A", "wrongemail", null, null);
    public static final String VIOLATED_VALIDATIONS_JSON_CTO = "{\"details\":[\"email has wrong value: must be a well-formed email address\",\"password has wrong value: must not be blank\",\"name has wrong value: size must be between 2 and 100\"]}";

    public static final UserUpdateTo WRONG_INPUTS_USER_UTO = new UserUpdateTo(USER_ID,"B","eml","wrng",null);
    public static final String VIOLATED_VALIDATIONS_JSON_UTO = "{\"details\":[\"password has wrong value: length must be between 5 and 32 characters\",\"email has wrong value: must be a well-formed email address\",\"name has wrong value: size must be between 2 and 100\"]}";

    public static final TestMatchers<UserViewTo> USER_VIEW_TO_TEST_MATCHERS = TestMatchers.useFieldsComparator(UserViewTo.class, "registered");
}

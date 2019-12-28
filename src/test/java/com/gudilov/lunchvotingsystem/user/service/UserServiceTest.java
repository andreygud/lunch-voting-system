package com.gudilov.lunchvotingsystem.user.service;

import com.gudilov.lunchvotingsystem.user.model.Role;
import com.gudilov.lunchvotingsystem.user.to.UserCreateTo;
import com.gudilov.lunchvotingsystem.user.to.UserUpdateTo;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.getRootCause;
import static com.gudilov.lunchvotingsystem.user.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void create() {
        List<UserViewTo> usersBefore = userService.getAll();

        UserViewTo createdUser = userService.create(USER_1_CTO);
        List<UserViewTo> usersAfter = userService.getAll();

        USER_VIEW_TO_TEST_MATCHERS.assertMatch(createdUser, USER1_VIEW_TO);
        assertNotNull(createdUser.getRegistered());

        assertEquals(usersBefore.size() + 1, usersAfter.size());
    }

    @Test
    void create_dublicateEmail() {
        assertThrows(DataIntegrityViolationException.class, () -> userService.create(USER_DUPLICATE_CTO));
    }

    @Test
    void create_inputFieldsValidation() {
        UserCreateTo wrongName =
                new UserCreateTo(null,"A","wrongemail",null,null);

        Set<String> constraintViolations =
                ((ConstraintViolationException) getRootCause(
                        assertThrows(Exception.class, () -> userService.create(wrongName))))
                .getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        assertThat(constraintViolations)
                .isEqualTo(Set.of("must not be blank",
                        "must be a well-formed email address",
                        "size must be between 2 and 100"));
    }

    @Test
    void update_changeName() {
        UserUpdateTo updatedName = new UserUpdateTo(USER_ID, "New Name", null, null, null);
        userService.update(updatedName);
        UserViewTo actualUpdatedUser = userService.get(USER_ID);
        UserViewTo expected = new UserViewTo(USER_VIEW_TO);
        expected.setName("New Name");

        USER_VIEW_TO_TEST_MATCHERS.assertMatch(actualUpdatedUser, expected);
    }

    @Test
    void update_changeRole() {
        UserUpdateTo updatedName = new UserUpdateTo(USER_ID, null, null, null, Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));
        userService.update(updatedName);
        UserViewTo actualUpdatedUser = userService.get(USER_ID);
        UserViewTo expected = new UserViewTo(USER_VIEW_TO);
        expected.setRoles(Set.of(Role.ROLE_USER, Role.ROLE_ADMIN));

        USER_VIEW_TO_TEST_MATCHERS.assertMatch(actualUpdatedUser, expected);
    }

    @Test
    void delete() {
        userService.delete(ADMIN_ID);
        USER_VIEW_TO_TEST_MATCHERS.assertMatch(userService.getAll(), USER_VIEW_TO);
    }

    @Test
    void get() {
        UserViewTo userActual = userService.get(ADMIN_ID);
        USER_VIEW_TO_TEST_MATCHERS.assertMatch(userActual, ADMIN_VIEW_TO);
    }

    @Test
    void getAll() {
        List<UserViewTo> usersActual = userService.getAll();
        USER_VIEW_TO_TEST_MATCHERS.assertMatch(usersActual, ADMIN_VIEW_TO, USER_VIEW_TO);
    }
}
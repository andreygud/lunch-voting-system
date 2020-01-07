package com.gudilov.lunchvotingsystem.user.web;

import com.gudilov.lunchvotingsystem.common.exceptions.NotFoundException;
import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.user.model.User;
import com.gudilov.lunchvotingsystem.user.service.UserService;
import com.gudilov.lunchvotingsystem.user.service.mapper.UserMapper;
import com.gudilov.lunchvotingsystem.user.to.UserUpdateTo;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.gudilov.lunchvotingsystem.user.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    public UserAdminRestControllerTest() {
        super(UserAdminRestController.REST_URL);
    }

    @Test
    void getAll() throws Exception {
        perform(doGet())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(USER_VIEW_TO_TEST_MATCHERS.contentJson(ADMIN_VIEW_TO, USER_VIEW_TO));
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userService.get(USER_ID));
    }

    @Test
    void get() throws Exception {
        perform(doGet(USER_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(USER_VIEW_TO_TEST_MATCHERS.contentJson(USER_VIEW_TO));
    }

    @Test
    void update() throws Exception {
        User updated = new User(USER);
        updated.setEmail("new@email.com");
        UserUpdateTo updateTo = UserMapper.INSTANCE.transformEntityIntoUpdateTo(updated);
        UserViewTo updatedToExpected = UserMapper.INSTANCE.transformEntityIntoViewTo(updated);

        perform(doPut(USER_ID).jsonBody(updateTo))
                .andDo(print())
                .andExpect(status().isNoContent());
        UserViewTo updatedViewToActual = userService.get(USER_ID);

        USER_VIEW_TO_TEST_MATCHERS.assertMatch(updatedViewToActual, updatedToExpected);
    }

    @Test
    void create() throws Exception {
        perform(doPost().jsonBody(USER_1_CTO))
                .andDo(print())
                .andExpect(status().isCreated());

        UserViewTo createdViewToActual = userService.get(USER1_ID);

        USER_VIEW_TO_TEST_MATCHERS.assertMatch(createdViewToActual, USER1_VIEW_TO);
    }

    @Test
    void update_bindValidationError() throws Exception {
        perform(doPut(USER_ID).jsonBody(WRONG_INPUTS_USER_UTO))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(VIOLATED_VALIDATIONS_JSON_UTO));
    }

    @Test
    void create_duplicate_conflictError() throws Exception {
        perform(doPost().jsonBody(USER_DUPLICATE_CTO))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().json("{\"errorMessage\":\"DataIntegrityViolationException\"}"));
    }

    @Test
    void create_bindValidationError() throws Exception {
        perform(doPost().jsonBody(WRONG_ALL_INPUT_USER_CTO))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(VIOLATED_VALIDATIONS_JSON_CTO));
    }
}
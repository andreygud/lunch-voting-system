package com.gudilov.lunchvotingsystem.user.web;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static com.gudilov.lunchvotingsystem.user.UserTestData.USER_ID;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(locations = "classpath:spring/spring-app-mock-service-test.xml")
class UserAdminRestErrorHandlingMockedServiceTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    public UserAdminRestErrorHandlingMockedServiceTest() {
        super(UserAdminRestController.REST_URL);
    }

    @Test
    void handleError() throws Exception {
        perform(doGet(USER_ID))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"errorMessage\":\"RuntimeException\",\"details\":[\"Cannot process the request\"]}"));
    }
}
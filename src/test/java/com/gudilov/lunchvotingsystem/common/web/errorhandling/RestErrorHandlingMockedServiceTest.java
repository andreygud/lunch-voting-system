package com.gudilov.lunchvotingsystem.common.web.errorhandling;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.user.UserTestData;
import com.gudilov.lunchvotingsystem.user.service.UserService;
import com.gudilov.lunchvotingsystem.user.web.UserAdminRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static com.gudilov.lunchvotingsystem.user.UserTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Test generic Error Handling using user service as an example
@SpringJUnitWebConfig(locations = "classpath:spring/spring-app-mock-service-test.xml")
class RestErrorHandlingMockedServiceTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    public RestErrorHandlingMockedServiceTest() {
        super(UserAdminRestController.REST_URL);
    }

    @Test
    void handleError() throws Exception {
        perform(doGet(USER_ID).basicAuth(UserTestData.ADMIN))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"errorMessage\":\"RuntimeException\",\"details\":[\"Cannot process the request\"]}"));
    }
}
package com.gudilov.lunchvotingsystem.common.web.errorhandling;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.user.service.UserService;
import com.gudilov.lunchvotingsystem.user.web.UserAdminRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Test generic Error Handling using user service as an example
class RestErrorHandlingTest extends AbstractControllerTest {

    @Autowired
    UserService userService;

    public RestErrorHandlingTest() {
        super(UserAdminRestController.REST_URL);
    }

    @Test
    void errorHandling_illegalRequestDataError() throws Exception {
        perform(doPost().jsonBody("gibberish string"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json("{\"errorMessage\":\"HttpMessageNotReadableException\"}"));
    }

    @Test
    void errorHandling_notFoundError() throws Exception {
        perform(doGet(100))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"errorMessage\":\"NotFoundException\",\"details\":[\"id=100\"]}"));
    }
}
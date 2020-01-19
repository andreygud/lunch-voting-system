package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.gudilov.lunchvotingsystem.common.utils.TestUtil.shiftTime;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.RESTAURANT3_ID;
import static com.gudilov.lunchvotingsystem.restaurant.VoteTestData.*;
import static com.gudilov.lunchvotingsystem.user.UserTestData.USER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractControllerTest {

    public VoteRestControllerTest() {
        super(VoteRestController.REST_URL);
    }

    @Test
    void vote_success() throws Exception {
        shiftTime(NEXTDAY_DATE_TIME_BEFORE1100);

        perform(doPost("?restaurantId="+RESTAURANT3_ID).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(SUCCESS_VOTE_TO));
    }

    @Test
    void vote_update_success() throws Exception {
        shiftTime(DATE_TIME_BEFORE1100);

        perform(doPost("?restaurantId="+RESTAURANT3_ID).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(SUCCESS_UPDATE_VOTE_TO));
    }

    @Test
    void vote_update_fail() throws Exception {
        shiftTime(DATE_TIME_AFTER1100);

        perform(doPost("?restaurantId="+RESTAURANT3_ID).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().json(RULE_VIOLATION_JSON));
    }

    @Test
    void vote_nonExistingRestaurant() throws Exception {
        shiftTime(NEXTDAY_DATE_TIME_BEFORE1100);

        perform(doPost("?restaurantId="+100012).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(NOT_FOUND_RESTAURANT_JSON));
    }

    @Test
    void lastVote() throws Exception {
        perform(doGet("/last").basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(INITIAL_USER_VOTE_TO));
    }

    @Test
    void get() throws Exception {
        perform(doGet(USER_UPDATE_VOTE_ID).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(INITIAL_USER_VOTE_TO));
    }

    @Test
    void get_wronguser() throws Exception {
        perform(doGet(INITIAL_ADMIN_VOTE_TO.getId()).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(NOTFOUND_VOTE_JSON));
    }

    @Test
    void votingResult() throws Exception {
        perform(doGet("result").basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(VOTING_RESULTS_JSON));
    }

    @Test
    void votingResult_withDate() throws Exception {
        perform(doGet("result?date=" + LocalDate.now().toString()).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(VOTING_RESULTS_JSON));
    }

    @Test
    void votingHistory() throws Exception {
        perform(doGet("history?start=" + LocalDate.now().toString()).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(VOTING_HISTORY_JSON));
    }
}
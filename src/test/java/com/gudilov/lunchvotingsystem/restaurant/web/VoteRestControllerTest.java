package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.gudilov.lunchvotingsystem.common.utils.TestUtil.shiftTime;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.RESTAURANT3_ID;
import static com.gudilov.lunchvotingsystem.restaurant.VoteTestData.*;
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

        perform(doPost(Integer.toString(RESTAURANT3_ID)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(SUCCESS_VOTE_TO));
    }

    @Test
    void vote_update_success() throws Exception {
        shiftTime(DATE_TIME_BEFORE1100);

        perform(doPost(Integer.toString(RESTAURANT3_ID)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(SUCCESS_UPDATE_VOTE_TO));
    }

    @Test
    void vote_update_fail() throws Exception {
        shiftTime(DATE_TIME_AFTER1100);

        perform(doPost(Integer.toString(RESTAURANT3_ID)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().json(RULE_VIOLATION_JSON));
    }

    @Test
    void lastVote() throws Exception {
        perform(doGet("/last"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(INITIAL_USER_VOTE_TO));
    }

    @Test
    void get() throws Exception {
        perform(doGet(USER_UPDATE_VOTE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(VOTE_VIEW_TO_TEST_MATCHERS.contentJson(INITIAL_USER_VOTE_TO));
    }

    @Test
    void get_wronguser() throws Exception {
        perform(doGet(INITIAL_ADMIN_VOTE_TO.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(NOTFOUND_VOTE_JSON));
    }

    @Test
    void votingResult() throws Exception {
        perform(doGet("result"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(VOTING_RESULTS_JSON));
    }

    @Test
    void votingResult_withDate() throws Exception {
        perform(doGet("result?date" + LocalDate.now().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(VOTING_RESULTS_JSON));
    }
}
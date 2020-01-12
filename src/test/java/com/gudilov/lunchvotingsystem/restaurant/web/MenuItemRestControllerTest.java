package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData.CACTUS_ITEM1_BURGER_VIEW_TO;
import static com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData.ITEM_TO_TEST_MATCHERS;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuItemRestControllerTest extends AbstractControllerTest {

    public MenuItemRestControllerTest() {
        super(MenuItemRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(MenuItemTestData.CACTUS_ITEM1_BURGER_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(ITEM_TO_TEST_MATCHERS.contentJson(CACTUS_ITEM1_BURGER_VIEW_TO));
    }

    @Test
    void history() throws Exception {
        perform(doGet("history?date" + LocalDate.now().toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MenuItemTestData.HISTORY_JSON));
    }
}
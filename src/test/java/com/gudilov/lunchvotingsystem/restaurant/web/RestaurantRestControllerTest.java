package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData;
import com.gudilov.lunchvotingsystem.user.UserTestData;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData.*;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.*;
import static com.gudilov.lunchvotingsystem.user.UserTestData.USER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantRestControllerTest extends AbstractControllerTest {

    public RestaurantRestControllerTest() {
        super(RestaurantRestController.REST_URL);
    }

    @Test
    void get() throws Exception {
        perform(doGet(RESTAURANT1_ID).basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_TEST_MATCHERS.contentJson(RESTAURANT1_TO));
    }

    @Test
    void getAll() throws Exception {
        perform(doGet().basicAuth(USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_TEST_MATCHERS.contentJson(RESTAURANT1_TO, RESTAURANT3_TO, RESTAURANT2_TO));
    }

    @Test
    void getMenu() throws Exception {
        perform(doGet(RESTAURANT1_ID + "/menu").basicAuth(USER))
                .andDo((print()))
                .andExpect(status().isOk())
                .andExpect(ITEM_TO_TEST_MATCHERS.contentJson(CACTUS_ITEM1_BURGER_VIEW_TO, CACTUS_ITEM2_BURGER_VIEW_TO, CACTUS_ITEM3_BURGER_VIEW_TO));
    }

    @Test
    void history() throws Exception {
        perform(doGet("menu_history?start=" + LocalDate.now().toString()).basicAuth(UserTestData.USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MenuItemTestData.HISTORY_JSON));
    }

    @Test
    void history_by_restaurant() throws Exception {
        perform(doGet("menu_history?start=" + LocalDate.now().toString()+"&restaurantId="+RESTAURANT1_ID).basicAuth(UserTestData.USER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MenuItemTestData.HISTORY_JSON_SINGLEREST));
    }
}
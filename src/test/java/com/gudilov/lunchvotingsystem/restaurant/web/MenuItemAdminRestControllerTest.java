package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData;
import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.service.RestaurantService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData.*;
import static com.gudilov.lunchvotingsystem.user.UserTestData.ADMIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MenuItemAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    MenuItemService menuItemService;

    @Autowired
    RestaurantService restaurantService;

    public MenuItemAdminRestControllerTest() {
        super(MenuItemAdminRestController.REST_URL);
    }

    @Test
    void delete() throws Exception {
        int before = restaurantService.getCurretDayMenuItems(RestaurantTestData.RESTAURANT1_ID).size();
        perform(doDelete(CACTUS_ITEM1_BURGER_ID).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isNoContent());
        int after = restaurantService.getCurretDayMenuItems(RestaurantTestData.RESTAURANT1_ID).size();

        assertEquals(before - 1, after);
    }

    @Test
    void update() throws Exception {
        perform(doPut(CACTUS_ITEM1_BURGER_ID).jsonBody(CACTUS_ITEM1_BURGER_UPDATE_TO).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isNoContent());
        MenuItemViewTo item = menuItemService.get(CACTUS_ITEM1_BURGER_ID);
        assertEquals(CACTUS_ITEM1_BURGER_UPDATE_VIEW_TO, item);
    }
}
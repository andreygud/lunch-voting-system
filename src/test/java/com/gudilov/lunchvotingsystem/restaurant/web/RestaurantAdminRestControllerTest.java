package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.common.exceptions.NotFoundException;
import com.gudilov.lunchvotingsystem.common.web.AbstractControllerTest;
import com.gudilov.lunchvotingsystem.restaurant.service.RestaurantService;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantAdminRestControllerTest extends AbstractControllerTest {

    @Autowired
    RestaurantService restaurantService;

    public RestaurantAdminRestControllerTest() {
        super(RestaurantAdminRestController.REST_URL);
    }

    @Test
    void delete() throws Exception {
        perform(doDelete(RESTAURANT1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT1_ID));
    }

    @Test
    void update() throws Exception {
        RestaurantTo updated = new RestaurantTo(RESTAURANT1_TO);
        updated.setName("newname");

        RestaurantTo updatedToExpected = new RestaurantTo(updated);

        perform(doPut(RESTAURANT1_ID).jsonBody(updated))
                .andDo(print())
                .andExpect(status().isNoContent());
        RestaurantTo updatedActual = restaurantService.get(RESTAURANT1_ID);

        RESTAURANT_TO_TEST_MATCHERS.assertMatch(updatedActual, updatedToExpected);
    }

    @Test
    void create() throws Exception {
        perform(doPost().jsonBody(RESTAURANT_CREATE_TO))
                .andDo(print())
                .andExpect(status().isCreated());

        RestaurantTo createdViewToActual = restaurantService.get(RESTAURANT_CREATED_ID);

        RESTAURANT_TO_TEST_MATCHERS.assertMatch(createdViewToActual, RESTAURANT_CREATED_TO);
    }

    @Test
    void update_bindValidationError() throws Exception {
        perform(doPut(RESTAURANT1_ID).jsonBody(RESTAURANT_WRONG_ALL_INPUT_CREATE_TO))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(WRONG_INPUT_JSON));
    }

    @Test
    void create_bindValidationError() throws Exception {
        perform(doPost().jsonBody(RESTAURANT_WRONG_ALL_INPUT_CREATE_TO))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(WRONG_INPUT_JSON));
    }
}
package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.getRootCause;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class RestaurantServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    void create() {
        List<RestaurantTo> restaurantsBefore = restaurantService.getAll();
        RestaurantTo createdRestaurant = restaurantService.create(RestaurantTestData.RESTAURANT_CREATE_TO);
        List<RestaurantTo> usersAfter = restaurantService.getAll();

        assertEquals(RESTAURANT_CREATED_TO, createdRestaurant);
        assertEquals(restaurantsBefore.size() + 1, usersAfter.size());
    }

    @Test
    void create_inputFieldsValidation() {
        Set<String> constraintViolations =
                ((ConstraintViolationException) getRootCause(
                        assertThrows(Exception.class, () -> restaurantService.create(RESTAURANT_WRONG_ALL_INPUT_CREATE_TO))))
                        .getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.toSet());

        assertEquals(RESTAURANT_CREATION_VIOLATIONS, constraintViolations);
    }

    @Test
    void update_changeName() {
        RestaurantTo updatedName = new RestaurantTo(RESTAURANT1_ID, "New Name", null, null);
        restaurantService.update(updatedName);
        RestaurantTo actualUpdated = restaurantService.get(RESTAURANT1_ID);
        RestaurantTo expected = new RestaurantTo(RESTAURANT1_TO);
        expected.setName("New Name");

        assertEquals(expected, actualUpdated);
    }

    @Test
    void delete() {
        restaurantService.delete(RESTAURANT1_ID);
        assertEquals(List.of(RESTAURANT3_TO, RESTAURANT2_TO), restaurantService.getAll());
    }

    @Test
    void get() {
        RestaurantTo userActual = restaurantService.get(RESTAURANT1_ID);
        assertEquals(RESTAURANT1_TO, userActual);
    }

    @Test
    void getAll() {
        List<RestaurantTo> usersActual = restaurantService.getAll();
        assertEquals(List.of(RESTAURANT1_TO, RESTAURANT3_TO, RESTAURANT2_TO), usersActual);
    }
}
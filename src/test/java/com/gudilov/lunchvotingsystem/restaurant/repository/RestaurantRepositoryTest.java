package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//todo remove this testing when Service Layer and WebLayer unit tests are ready
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    void testCrudRepository() {

        List<Restaurant> restaurants = restaurantRepository.getAll();

        assertEquals(3,restaurants.size());

    }


}
package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.restaurant.repository.menuitem.MenuItemRepository;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.gudilov.lunchvotingsystem.common.utils.TestUtil.shiftTime;
import static com.gudilov.lunchvotingsystem.restaurant.MenuItemTestData.*;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static com.gudilov.lunchvotingsystem.restaurant.VoteTestData.NEXTDAY_DATE_TIME_BEFORE1100;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class MenuItemServiceTest {

    @Autowired
    private MenuItemRepository repository;

    @Autowired
    private MenuItemService service;

    @Test
    void create() {
        MenuItemTo item = new MenuItemTo(null, "my dish", 23);
        service.create(item, RESTAURANT1_ID);
        int actual = repository.getAllForToday(RESTAURANT1_ID).size();
        assertEquals(4, actual);
    }

    @Test
    void create_duplicate() {
        MenuItemTo item = new MenuItemTo(null, CACTUS_ITEM1_BURGER_VIEW_TO.getName(), 45);
        assertThrows(DataIntegrityViolationException.class,()->service.create(item, RESTAURANT1_ID));
    }

    @Test
    void create_duplicate_nextday() {
        shiftTime(NEXTDAY_DATE_TIME_BEFORE1100);
        MenuItemTo item = new MenuItemTo(null, CACTUS_ITEM1_BURGER_VIEW_TO.getName(), 45);
        service.create(item, RESTAURANT1_ID);
    }

    @Test
    void update() {
        int before = repository.getAllForToday(RESTAURANT1_ID).size();
        service.update(CACTUS_ITEM1_BURGER_UPDATE_TO, CACTUS_ITEM1_BURGER_ID);
        int after = repository.getAllForToday(RESTAURANT1_ID).size();
        assertEquals(before, after);
    }

    @Test
    void delete() {
        int before = repository.getAllForToday(RESTAURANT1_ID).size();
        service.delete(CACTUS_ITEM1_BURGER_ID);
        int after = repository.getAllForToday(RESTAURANT1_ID).size();
        assertEquals(before - 1, after);
    }

    @Test
    void deleteAll() {
        service.deleteAllForToday(RESTAURANT1_ID);
        int after = repository.getAllForToday(RESTAURANT1_ID).size();
        assertEquals(0, after);
    }

    @Test
    void get() {
        MenuItemViewTo item = service.get(CACTUS_ITEM1_BURGER_ID);
        assertEquals(CACTUS_ITEM1_BURGER_VIEW_TO, item);
    }


}
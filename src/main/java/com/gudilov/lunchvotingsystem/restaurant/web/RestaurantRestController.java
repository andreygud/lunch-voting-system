package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.service.RestaurantService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/restaurant";

    RestaurantService restaurantService;
    MenuItemService menuItemService;

    public RestaurantRestController(RestaurantService restaurantService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.debug("rest get id={}", id);
        return restaurantService.get(id);
    }

    @GetMapping("")
    public List<RestaurantTo> getAll() {
        log.debug("rest getAll");
        return restaurantService.getAll();
    }

    @GetMapping("/{id}/menu")
    public List<MenuItemViewTo> getMenu(@PathVariable int id) {
        log.debug("rest get menu  restaurant id={}", id);
        return menuItemService.getAll(id);
    }
}



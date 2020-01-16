package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.service.RestaurantService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(RestaurantAdminRestController.REST_URL)
public class RestaurantAdminRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/restaurant";

    RestaurantService restaurantService;
    MenuItemService menuItemService;

    public RestaurantAdminRestController(RestaurantService restaurantService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("rest delete id={}", id);
        restaurantService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.debug("rest update RestaurantTo={}", restaurantTo);
        restaurantService.update(restaurantTo, id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestaurantTo> create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.debug("rest create RestaurantTo={}", restaurantTo);
        RestaurantTo restaurant = restaurantService.create(restaurantTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurant);
    }

    @PostMapping("/{id}/menu")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MenuItemViewTo> createMenuItem(@Valid @RequestBody MenuItemTo menuItemTo, @PathVariable int id) {
        log.debug("rest create menuitem restId={}", id);
        MenuItemViewTo menuItemViewTo = menuItemService.create(menuItemTo, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MenuItemRestController.REST_URL + "/{miId}")
                .buildAndExpand(menuItemViewTo.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(menuItemViewTo);
    }

    @DeleteMapping("/{id}/menu")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodayMenu(@PathVariable int id) {
        log.debug("rest restaurant delete Menu restId={}", id);
        menuItemService.deleteAllForToday(id);
    }
}



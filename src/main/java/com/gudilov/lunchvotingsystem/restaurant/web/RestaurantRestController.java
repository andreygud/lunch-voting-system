package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.restaurant.reports.ReportingRepository;
import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.service.RestaurantService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemHistoryTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    public static final int DEFAULT_HISTORY_DEPTH = 14;


    static final String REST_URL = "/rest/restaurant";

    private RestaurantService restaurantService;
    private MenuItemService menuItemService;
    private ReportingRepository reportingRepository;

    public RestaurantRestController(RestaurantService restaurantService, MenuItemService menuItemService, ReportingRepository reportingRepository) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
        this.reportingRepository = reportingRepository;
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

    @GetMapping("/menu_history")
    public List<MenuItemHistoryTo> history(
            @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam @Nullable Integer restaurantId){
        log.debug("rest get menuitems history startdate={}", start);

        if(start == null){
            start = LocalDate.now().minusDays(DEFAULT_HISTORY_DEPTH);
        }
        return reportingRepository.getMenuHistory(start,restaurantId);
    }
}



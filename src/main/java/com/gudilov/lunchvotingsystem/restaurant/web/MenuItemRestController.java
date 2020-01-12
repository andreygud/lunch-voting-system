package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MenuItemRestController.REST_URL)
public class MenuItemRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/menuItems/";

    private MenuItemService menuItemService;

    public MenuItemRestController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @RequestMapping("/{id}")
    public MenuItemViewTo get(@PathVariable int id) {
        log.debug("rest get menuitem id={}", id);
        return menuItemService.get(id);
    }
}

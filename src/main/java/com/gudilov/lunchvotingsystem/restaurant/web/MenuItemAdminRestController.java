package com.gudilov.lunchvotingsystem.restaurant.web;

import com.gudilov.lunchvotingsystem.restaurant.service.MenuItemService;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(MenuItemAdminRestController.REST_URL)
public class MenuItemAdminRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/admin/menuItems/";

    private MenuItemService menuItemService;

    public MenuItemAdminRestController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("rest menu_item delete id={}", id);
        menuItemService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MenuItemTo item, @PathVariable int id) {
        log.debug("rest menu_item update id={}", id);
        menuItemService.update(item, id);
    }
}


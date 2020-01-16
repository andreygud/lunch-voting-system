package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;
import com.gudilov.lunchvotingsystem.restaurant.repository.menuitem.MenuItemRepository;
import com.gudilov.lunchvotingsystem.restaurant.service.mapper.MenuItemsMapper;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemTo;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.*;
import static org.springframework.util.Assert.notNull;

@Service
public class MenuItemService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MenuItemsMapper menuItemMapper;
    private MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemsMapper menuItemMapper, MenuItemRepository menuItemRepository) {
        this.menuItemMapper = menuItemMapper;
        this.menuItemRepository = menuItemRepository;
    }

    public MenuItemViewTo create(MenuItemTo menuItemTo, int restaurantId) {
        log.debug("create {}", menuItemTo);
        notNull(menuItemTo, "menu item must not be null");
        checkNew(menuItemTo);
        MenuItem menuItem = menuItemRepository.save(menuItemMapper.transformToIntoEntity(menuItemTo), restaurantId);
        return menuItemMapper.transformEntityIntoViewTo(menuItem);
    }

    @Transactional
    public void update(MenuItemTo menuItemTo, int id) {
        log.debug("update {}", menuItemTo);
        notNull(menuItemTo, "menu items must not be null");
        assureIdConsistent(menuItemTo, id);
        MenuItem menuItem = checkNotFoundWithId(menuItemRepository.get(id), menuItemTo.getId());
        menuItemMapper.updateExistingEntity(menuItemTo, menuItem);
    }

    public void delete(int id) {
        log.debug("delete {}", id);
        checkNotFoundWithId(menuItemRepository.delete(id), id);
    }

    public void deleteAllForToday(int restaurantId) {
        log.debug("delete All by restaurantId={}", restaurantId);
        checkNotFoundWithId(menuItemRepository.deleteAllForToday(restaurantId), restaurantId);
    }

    public MenuItemViewTo get(int id) {
        log.debug("get {}", id);
        MenuItem item = checkNotFoundWithId(menuItemRepository.get(id), id);
        return menuItemMapper.transformEntityIntoViewTo(item);
    }

    public List<MenuItemViewTo> getAllForToday(int restaurantId) {
        log.debug("getAll");
        List<MenuItem> items = menuItemRepository.getAllForToday(restaurantId);
        return menuItemMapper.transformEntitiesIntoViewTos(items);
    }
}

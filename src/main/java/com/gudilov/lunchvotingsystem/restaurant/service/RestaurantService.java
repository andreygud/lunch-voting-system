package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;
import com.gudilov.lunchvotingsystem.restaurant.model.Restaurant;
import com.gudilov.lunchvotingsystem.restaurant.repository.restaurant.RestaurantRepository;
import com.gudilov.lunchvotingsystem.restaurant.service.mapper.MenuItemsMapper;
import com.gudilov.lunchvotingsystem.restaurant.service.mapper.RestaurantMapper;
import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemViewTo;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.*;
import static org.springframework.util.Assert.notNull;

@Service
public class RestaurantService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantMapper restaurantMapper;
    private RestaurantRepository restaurantRepository;
    private MenuItemsMapper menuItemMapper;


    public RestaurantService(RestaurantMapper restaurantMapper, RestaurantRepository restaurantRepository, MenuItemsMapper menuItemMapper) {
        this.restaurantMapper = restaurantMapper;
        this.restaurantRepository = restaurantRepository;
        this.menuItemMapper = menuItemMapper;
    }

    public RestaurantTo create(RestaurantTo restaurantTo) {
        log.debug("create {}", restaurantTo);
        notNull(restaurantTo, "restaurant must not be null");
        checkNew(restaurantTo);
        Restaurant restaurant = restaurantRepository.save(restaurantMapper.transformToIntoEntity(restaurantTo));
        return restaurantMapper.transformEntityIntoViewTo(restaurant);
    }

    @Transactional
    public void update(RestaurantTo restaurantTo, int id) {
        log.debug("update {}", restaurantTo);
        notNull(restaurantTo, "restaurant must not be null");
        assureIdConsistent(restaurantTo, id);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(restaurantTo.getId()), restaurantTo.getId());
        restaurantMapper.updateExistingEntity(restaurantTo, restaurant);
    }

    public void delete(int id) {
        log.debug("delete {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public RestaurantTo get(int id) {
        log.debug("get {}", id);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(id), id);
        return restaurantMapper.transformEntityIntoViewTo(restaurant);
    }

    public List<RestaurantTo> getAll() {
        log.debug("getAll");
        List<Restaurant> restaurants = restaurantRepository.getAll();
        return restaurantMapper.transformEntitiesIntoViewTos(restaurants);
    }

    public List<MenuItemViewTo> getCurretDayMenuItems(int restaurantId) {
        log.debug("getAll menu items for current date");
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getWithItems(restaurantId),restaurantId);
        List<MenuItem> items = restaurant.getTodayItems();
        return menuItemMapper.transformEntitiesIntoViewTos(items);
    }
}

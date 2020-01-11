package com.gudilov.lunchvotingsystem.restaurant.repository.menuitem;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;

import java.util.List;

public interface MenuItemRepository {

    // empty list if not found
    List<MenuItem> getAll(int restaurantId);

    // null if not found
    MenuItem get(int id);

    MenuItem save(MenuItem item, int restaurantId);

    // false if not found
    boolean delete(int id);

    boolean deleteAll(int restaurant);
}

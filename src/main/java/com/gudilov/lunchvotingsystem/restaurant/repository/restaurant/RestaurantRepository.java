package com.gudilov.lunchvotingsystem.restaurant.repository.restaurant;

import com.gudilov.lunchvotingsystem.restaurant.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    // null if not found, when updated
    Restaurant save(Restaurant user);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    // null if not found
    Restaurant getWithItems(int id);

}
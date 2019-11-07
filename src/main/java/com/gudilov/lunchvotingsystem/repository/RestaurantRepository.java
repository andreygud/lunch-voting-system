package com.gudilov.lunchvotingsystem.repository;

import com.gudilov.lunchvotingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    public List<Restaurant> getAll(int userId);

    public Restaurant save(Restaurant vote, int userId);
}

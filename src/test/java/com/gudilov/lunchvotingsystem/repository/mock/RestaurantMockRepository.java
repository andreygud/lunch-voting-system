package com.gudilov.lunchvotingsystem.repository.mock;

import com.gudilov.lunchvotingsystem.model.Restaurant;
import com.gudilov.lunchvotingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantMockRepository extends GrantSeparatedMockRepository<Restaurant> implements RestaurantRepository {
}

package com.gudilov.lunchvotingsystem.restaurant.repository.restaurant;

import com.gudilov.lunchvotingsystem.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("select distinct r from Restaurant r left join fetch r.todayItems where r.id = :restaurantId")
    Restaurant getWithItems(@Param("restaurantId")int restaurantId);
}
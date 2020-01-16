package com.gudilov.lunchvotingsystem.restaurant.repository.menuitem;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query("SELECT mi FROM MenuItem mi WHERE mi.restaurant.id=:restaurantId and mi.menuDate = current_date()")
    List<MenuItem> findAllByRestaurantIdOrderByName(@Param("restaurantId") int restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem mi WHERE mi.id=:id")
    int deleteById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem mi WHERE mi.restaurant.id=:restaurantId and mi.menuDate = current_date()")
    int deleteAllByRestaurantId(@Param("restaurantId") int restaurantId);
}

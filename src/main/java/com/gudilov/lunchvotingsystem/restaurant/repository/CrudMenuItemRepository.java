package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudMenuItemRepository extends JpaRepository<MenuItem,Integer> {
}

package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudMenuRepository extends JpaRepository<Menu,Integer> {
}

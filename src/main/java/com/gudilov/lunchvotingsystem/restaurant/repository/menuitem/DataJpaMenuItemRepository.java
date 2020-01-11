package com.gudilov.lunchvotingsystem.restaurant.repository.menuitem;

import com.gudilov.lunchvotingsystem.restaurant.model.MenuItem;
import com.gudilov.lunchvotingsystem.restaurant.repository.restaurant.CrudRestaurantRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMenuItemRepository implements MenuItemRepository {
    private CrudMenuItemRepository crudMenuItemRepository;
    private CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuItemRepository(CrudMenuItemRepository crudMenuItemRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudMenuItemRepository = crudMenuItemRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public List<MenuItem> getAll(int restaurantId) {
        return crudMenuItemRepository.findAllByRestaurantIdOrderByName(restaurantId);
    }

    @Override
    public MenuItem get(int id) {
        return crudMenuItemRepository.findById(id).orElse(null);
    }

    @Override
    public MenuItem save(MenuItem item, int restaurantId) {
        if (!item.isNew() && get(item.getId()) == null) {
            return null;
        }
        item.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuItemRepository.save(item);
    }

    @Override
    public boolean delete(int id) {
        return crudMenuItemRepository.deleteById(id) != 0;
    }

    @Override
    public boolean deleteAll(int restaurantId) {
        return crudMenuItemRepository.deleteAllByRestaurantId(restaurantId) != 0;
    }

}

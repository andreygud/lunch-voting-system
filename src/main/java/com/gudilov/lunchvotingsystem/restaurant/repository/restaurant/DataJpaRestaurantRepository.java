package com.gudilov.lunchvotingsystem.restaurant.repository.restaurant;

import com.gudilov.lunchvotingsystem.restaurant.model.Restaurant;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.gudilov.lunchvotingsystem.restaurant.model.Restaurant.CURRENT_DAY_FILTER;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private CrudRestaurantRepository crudRepository;

    private EntityManager entityManager;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRepository, EntityManager entityManager) {
        this.crudRepository = crudRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Restaurant save(Restaurant user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    @Override
    @Transactional
    public Restaurant getWithItems(int id) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter(CURRENT_DAY_FILTER);
        return crudRepository.getWithItems(id);
    }
}

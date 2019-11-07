package com.gudilov.lunchvotingsystem.repository.mock;

import com.gudilov.lunchvotingsystem.model.AbstractBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class GrantSeparatedMockRepository<T extends AbstractBaseEntity> {

        private static final Logger log = LoggerFactory.getLogger(GrantSeparatedMockRepository.class);

        private Map<Integer, MockRepository<T>> usersMealsMap = new ConcurrentHashMap<>();

        public T save(T meal, int userId) {
            Objects.requireNonNull(meal, "meal must not be null");
            MockRepository<T> meals = usersMealsMap.computeIfAbsent(userId, uid -> new  MockRepository<>());
            return meals.save(meal);
        }

        public boolean delete(int id, int userId) {
            MockRepository<T> meals = usersMealsMap.get(userId);
            return meals != null && meals.delete(id);
        }

        public T get(int id, int userId) {
            MockRepository<T> meals = usersMealsMap.get(userId);
            return meals == null ? null : meals.get(id);
        }

        public List<T> getAll(int userId) {
            MockRepository<T> meals = usersMealsMap.get(userId);
            return meals == null ? Collections.emptyList() :
                    new ArrayList<>(meals.getCollection());
        }



}

package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.model.Vote;
import com.gudilov.lunchvotingsystem.user.repository.CrudUserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private static final Sort SORT_ID = Sort.by(Sort.Direction.ASC, "id");

    private CrudVoteRepository crudVoteRepository;
    private CrudRestaurantRepository crudRestaurantRepository;
    private CrudUserRepository crudUserRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (!vote.isNew() && get(userId, vote.getId()) == null) {
            return null;
        }
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public List<Vote> getAllBetweenByUserId(int userId, LocalDateTime start, LocalDateTime end) {
        return crudVoteRepository.getAllBetweenByUserId(userId, start, end);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll(SORT_ID);
    }

    @Override
    public Vote getLast(int userId) {
        return crudVoteRepository.getDistinctTopByUserIdOrderByVoteTimeDesc(userId);
    }

    @Override
    public Vote get(int userId, int id) {
        return crudVoteRepository.getByUserIdAndId(userId, id);
    }
}

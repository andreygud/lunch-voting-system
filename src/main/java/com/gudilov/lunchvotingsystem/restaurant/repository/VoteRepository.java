package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int userId, int restaurantId);

    // null if not found
    List<Vote> getAllBetweenByUserId(int userId, LocalDateTime start, LocalDateTime end);

    // null if not found
    List<Vote> getAll();
}

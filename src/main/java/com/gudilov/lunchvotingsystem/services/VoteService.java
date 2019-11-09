package com.gudilov.lunchvotingsystem.services;

import com.gudilov.lunchvotingsystem.model.Vote;
import com.gudilov.lunchvotingsystem.repository.CrudVoteRepository;
import com.gudilov.lunchvotingsystem.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private CrudVoteRepository crudVoteRepository;

    public VoteService(CrudVoteRepository crudVoteRepository) {
        this.crudVoteRepository = crudVoteRepository;
    }

    public Vote vote(String restaurantId, int userId) {
        Objects.requireNonNull(restaurantId,"Restaurant cannot be null");
        Vote vote = new Vote(restaurantId, LocalDateTime.now(),userId);

        return crudVoteRepository.save(vote);
    }

    public Map<String, Integer> getTodayResults() {
        List<Vote> votes = crudVoteRepository.getAllAfterDateTime(DateTimeUtil.startOfDay(LocalDate.now()));

        return votes.stream().collect(Collectors.groupingBy(Vote::getRestaurantID, Collectors.summingInt(value -> 1)));
    }
}

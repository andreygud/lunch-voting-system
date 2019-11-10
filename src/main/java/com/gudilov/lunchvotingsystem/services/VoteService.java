package com.gudilov.lunchvotingsystem.services;

import com.gudilov.lunchvotingsystem.exceptions.BusinessRuleViolationException;
import com.gudilov.lunchvotingsystem.model.Vote;
import com.gudilov.lunchvotingsystem.repository.CrudVoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private CrudVoteRepository crudVoteRepository;
    private DateAndTimeService dateAndTimeService;

    public VoteService(CrudVoteRepository crudVoteRepository, DateAndTimeService dateAndTimeService) {
        this.crudVoteRepository = crudVoteRepository;
        this.dateAndTimeService = dateAndTimeService;
    }

    public Vote vote(String restaurantId, int userId) {
        Objects.requireNonNull(restaurantId,"Restaurant cannot be null");

        List<Vote> todayVotes = crudVoteRepository.getAllForDateAndUser(dateAndTimeService.startOfToday(),userId);

        if(todayVotes.size() > 0)
            throw new BusinessRuleViolationException("The user has already voted today");

        Vote vote = new Vote(restaurantId, LocalDateTime.now(),userId);
        return crudVoteRepository.save(vote);
    }

    public Map<String, Integer> getTodayResults() {
        List<Vote> votes = crudVoteRepository.getAllAfterDateTime(dateAndTimeService.startOfToday());

        return votes.stream().collect(Collectors.groupingBy(Vote::getRestaurantID, Collectors.summingInt(value -> 1)));
    }
}

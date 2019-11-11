package com.gudilov.lunchvotingsystem.vote.services;

import com.gudilov.lunchvotingsystem.vote.exceptions.BusinessRuleViolationException;
import com.gudilov.lunchvotingsystem.vote.model.Vote;
import com.gudilov.lunchvotingsystem.vote.repository.CrudVoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Transactional
    public Vote vote(String restaurantId, int userId) {
        Objects.requireNonNull(restaurantId, "Restaurant cannot be null");

        List<Vote> todayVotes = crudVoteRepository.getAllForDateAndUser(dateAndTimeService.getCurrentDate(), userId);

        Vote vote = null;
        LocalTime currentTime = dateAndTimeService.currentTime();
        LocalDate currentDate = dateAndTimeService.getCurrentDate();

        if (currentTime.isBefore(LocalTime.of(11, 0))) {
            if (todayVotes.size() > 0) {
                vote = todayVotes.get(0);
                vote.setRestaurantID(restaurantId);
                vote.setVoteDate(currentDate);
                vote.setVoteTime(currentTime);
            } else {
                vote = new Vote(restaurantId, currentDate,currentTime, userId);
            }
        } else {
            throw new BusinessRuleViolationException("It is too late, you cannot vote or the vote can't be changed");
        }

        return crudVoteRepository.save(vote);
    }

    public Map<String, Integer> getTodayResults() {
        List<Vote> votes = crudVoteRepository.getAllAfterDateTime(dateAndTimeService.getCurrentDate());

        return votes.stream().collect(Collectors.groupingBy(Vote::getRestaurantID, Collectors.summingInt(value -> 1)));
    }
}

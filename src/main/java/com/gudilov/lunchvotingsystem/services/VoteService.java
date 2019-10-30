package com.gudilov.lunchvotingsystem.services;

import com.gudilov.lunchvotingsystem.model.Restaurant;
import com.gudilov.lunchvotingsystem.model.Vote;
import com.gudilov.lunchvotingsystem.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void vote(int restaurantId, int userId) {
        //voteRepository.create()

    }

    public Map<Integer, Long> getTodaysSummaryByRestaurant(int userId) {
        List<Vote> votes = voteRepository.getAll(userId);
        return votes.stream().collect(Collectors.groupingBy(Vote::getRestaurantID,Collectors.counting()));
    }
}

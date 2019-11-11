package com.gudilov.lunchvotingsystem.vote.web;

import com.gudilov.lunchvotingsystem.vote.model.Vote;
import com.gudilov.lunchvotingsystem.vote.services.AuthorizationService;
import com.gudilov.lunchvotingsystem.vote.services.RestaurantService;
import com.gudilov.lunchvotingsystem.vote.services.VoteService;

import java.util.List;


public class VoteController {

    private AuthorizationService authorizationService;
    private RestaurantService restaurantService;
    private VoteService voteService;

    public VoteController(AuthorizationService authorizationService, RestaurantService restaurantService, VoteService voteService) {
        this.authorizationService = authorizationService;
        this.restaurantService = restaurantService;
        this.voteService = voteService;
    }

    public List<Vote> getVotingResults() {

 /*       int userId = authorizationService.getAuthUserId();

        List<Restaurant> restaurants = restaurantService.getAll(userId);

        Map<Integer, Long> votesSummary = voteService.getTodaysSummaryByRestaurant(userId);

        return restaurants.stream()
                .map(restaurant ->
                        new VoteResult(
                                restaurant.getId(),
                                restaurant.getRestaurantName(),
                                votesSummary.get(restaurant.getId())))
                .collect(Collectors.toList());*/
        return null;
    }

    public void vote(String restaurantId) {
        voteService.vote(restaurantId, authorizationService.getAuthUserId());
    }
}

package com.gudilov.lunchvotingsystem.web;

import com.gudilov.lunchvotingsystem.dto.VoteResult;
import com.gudilov.lunchvotingsystem.model.Restaurant;
import com.gudilov.lunchvotingsystem.services.AuthorizationService;
import com.gudilov.lunchvotingsystem.services.RestaurantService;
import com.gudilov.lunchvotingsystem.services.VoteService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class VoteController {

    private AuthorizationService authorizationService;
    private RestaurantService restaurantService;
    private VoteService voteService;

    public VoteController(AuthorizationService authorizationService, RestaurantService restaurantService, VoteService voteService) {
        this.authorizationService = authorizationService;
        this.restaurantService = restaurantService;
        this.voteService = voteService;
    }

    public List<VoteResult> getVotingResults() {

        int userId = authorizationService.getAuthUserId();

        List<Restaurant> restaurants = restaurantService.getAll(userId);

        Map<Integer, Long> votesSummary = voteService.getTodaysSummaryByRestaurant(userId);

        return restaurants.stream()
                .map(restaurant ->
                        new VoteResult(
                                restaurant.getId(),
                                restaurant.getRestaurantName(),
                                votesSummary.get(restaurant.getId())))
                .collect(Collectors.toList());
    }

    public void vote(int restaurantId) {
        voteService.vote(restaurantId, authorizationService.getAuthUserId());
    }
}

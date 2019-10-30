package com.gudilov.lunchvotingsystem.dto;

public class VoteResult {

    private int restaurantID;
    private String restaurantName;
    private Long votesNumber;

    public VoteResult(int restaurantID, String restaurantName, Long votesNumber) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.votesNumber = votesNumber;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(Long votesNumber) {
        this.votesNumber = votesNumber;
    }
}

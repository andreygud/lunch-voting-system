package com.gudilov.lunchvotingsystem.dto;

import java.time.LocalDateTime;

public class VoteResult {

    private int restaurantID;
    private String restaurantName;
    private LocalDateTime timestamp;
    private int votesNumber;

    public VoteResult(int restaurantID, String restaurantName, LocalDateTime timestamp, int votesNumber) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(int votesNumber) {
        this.votesNumber = votesNumber;
    }
}

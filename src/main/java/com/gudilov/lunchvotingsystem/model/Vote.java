package com.gudilov.lunchvotingsystem.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{

    private int restaurantID;
    private LocalDateTime timestamp;
    private int userId;

    public Vote(Integer id, int restaurantID, LocalDateTime timestamp, int userId) {
        super(id);
        this.restaurantID = restaurantID;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public Vote() {
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

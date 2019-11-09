package com.gudilov.lunchvotingsystem.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Vote extends AbstractBaseEntity{

    private String restaurantID;

    private LocalDateTime votingtime;

    @NotNull
    private int userId;

    public Vote(String restaurantID, LocalDateTime votingtime, int userId) {
        this(null,restaurantID, votingtime,userId);
    }

    public Vote(Integer id, String restaurantID, LocalDateTime votingtime, int userId) {
        super(id);
        this.restaurantID = restaurantID;
        this.votingtime = votingtime;
        this.userId = userId;
    }

    public Vote() {
        super();
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public LocalDateTime getVotingtime() {
        return votingtime;
    }

    public void setVotingtime(LocalDateTime timestamp) {
        this.votingtime = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

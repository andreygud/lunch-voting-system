package com.gudilov.lunchvotingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userId","voteDate"},name="vote_unique_userid_votedate_idx")})
public class Vote extends AbstractBaseEntity{

    @NotBlank
    private String restaurantID;

    @NotNull
    private LocalDate voteDate;

    @NotNull
    private LocalTime voteTime;

    @NotNull
    private int userId;

    public Vote(String restaurantID, LocalDate voteDate, LocalTime voteTime,  int userId) {
        this(null,restaurantID, voteDate, voteTime,userId);
    }

    public Vote(Integer id, @NotBlank String restaurantID, @NotNull LocalDate voteDate, @NotNull LocalTime voteTime, @NotNull int userId) {
        super(id);
        this.restaurantID = restaurantID;
        this.voteDate = voteDate;
        this.voteTime = voteTime;
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

    public LocalDate getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(LocalDate timestamp) {
        this.voteDate = timestamp;
    }

    public LocalTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalTime voteTime) {
        this.voteTime = voteTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

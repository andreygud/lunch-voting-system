package com.gudilov.lunchvotingsystem.restaurant.to;

import java.time.LocalDateTime;

public class VotingHistoryTo {
    private static final long serialVersionUID = 1L;

    private LocalDateTime voteDateTime;

    private int userId;
    private String userName;

    private int restaurantId;
    private String restaurantName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDateTime getVoteDateTime() {
        return voteDateTime;
    }

    public void setVoteDateTime(LocalDateTime voteDateTime) {
        this.voteDateTime = voteDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotingHistoryTo that = (VotingHistoryTo) o;

        if (userId != that.userId) return false;
        if (restaurantId != that.restaurantId) return false;
        if (voteDateTime != null ? !voteDateTime.equals(that.voteDateTime) : that.voteDateTime != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return restaurantName != null ? restaurantName.equals(that.restaurantName) : that.restaurantName == null;
    }

    @Override
    public int hashCode() {
        int result = voteDateTime != null ? voteDateTime.hashCode() : 0;
        result = 31 * result + userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + restaurantId;
        result = 31 * result + (restaurantName != null ? restaurantName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VotingHistoryTo{" +
                "dateTime=" + voteDateTime +
                ", userId=" + userId +
                ", userName=" + userName +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }
}

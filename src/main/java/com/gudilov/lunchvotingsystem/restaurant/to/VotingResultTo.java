package com.gudilov.lunchvotingsystem.restaurant.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VotingResultTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dateTime;
    private int restaurantId;
    private String restaurantName;
    private int votesCount;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    @Override
    public String toString() {
        return "VotingResultTo{" +
                "dateTime=" + dateTime +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", votes=" + votesCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotingResultTo that = (VotingResultTo) o;

        if (restaurantId != that.restaurantId) return false;
        if (votesCount != that.votesCount) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        return restaurantName != null ? restaurantName.equals(that.restaurantName) : that.restaurantName == null;
    }

    @Override
    public int hashCode() {
        int result = dateTime != null ? dateTime.hashCode() : 0;
        result = 31 * result + restaurantId;
        result = 31 * result + (restaurantName != null ? restaurantName.hashCode() : 0);
        result = 31 * result + votesCount;
        return result;
    }
}

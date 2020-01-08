package com.gudilov.lunchvotingsystem.restaurant.to;

import com.gudilov.lunchvotingsystem.common.to.BaseTo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VoteViewTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime voteTime;

    private String userName;

    private Integer userId;

    private String restaurantName;

    private Integer restaurantId;

    public VoteViewTo(Integer id, LocalDateTime voteTime, String userName, Integer userId, String restaurantName, Integer restaurantId) {
        super(id);
        this.voteTime = voteTime;
        this.userName = userName;
        this.userId = userId;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
    }

    public VoteViewTo() {
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
        this.voteTime = voteTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "VoteViewTo{" +
                "voteTime=" + voteTime +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VoteViewTo that = (VoteViewTo) o;

        if (voteTime != null ? !voteTime.equals(that.voteTime) : that.voteTime != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (restaurantName != null ? !restaurantName.equals(that.restaurantName) : that.restaurantName != null)
            return false;
        return restaurantId != null ? restaurantId.equals(that.restaurantId) : that.restaurantId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (voteTime != null ? voteTime.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (restaurantName != null ? restaurantName.hashCode() : 0);
        result = 31 * result + (restaurantId != null ? restaurantId.hashCode() : 0);
        return result;
    }
}

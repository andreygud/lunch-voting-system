package com.gudilov.lunchvotingsystem.model;

public class Restaurant extends AbstractBaseEntity {
    private String restaurantName;

    public Restaurant(Integer id, String restaurantName) {
        super(id);
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}

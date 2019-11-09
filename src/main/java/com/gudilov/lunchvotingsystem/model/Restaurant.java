package com.gudilov.lunchvotingsystem.model;

import javax.persistence.Entity;

@Entity
public class Restaurant extends AbstractBaseEntity {
    private String restaurantName;

    public Restaurant(Integer id, String restaurantName) {
        super(id);
        this.restaurantName = restaurantName;
    }
    public Restaurant(){
        super();
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}

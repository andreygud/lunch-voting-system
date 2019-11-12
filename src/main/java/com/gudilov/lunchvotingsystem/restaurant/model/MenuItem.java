package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractBaseEntity;

import javax.persistence.Entity;

@Entity
public class MenuItem extends AbstractBaseEntity {

    private String dishName;
    //consider bigdecimal here in the future
    private long price;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String description) {
        this.dishName = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}

package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractBaseEntity;

import javax.persistence.Entity;

@Entity
public class MenuItem extends AbstractBaseEntity {

    private String description;
    //consider bigdecimal here in the future
    private long price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}

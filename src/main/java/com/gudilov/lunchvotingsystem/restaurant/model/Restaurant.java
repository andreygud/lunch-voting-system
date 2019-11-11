package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Restaurant extends AbstractBaseEntity {

    private String restaurantName;

    private String description;

    private String address;

    @OneToOne
    private Menu menu;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

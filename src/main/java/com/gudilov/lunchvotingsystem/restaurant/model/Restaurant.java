package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity{

    @Column(name = "description")
    @Size(max = 1000)
    private String description;

    @Column(name = "address", nullable = false)
    @Size(min = 5, max = 255)
    private String address;



    public Restaurant(Integer id, String name, String description, String address) {
        super(id, name);
        setDescription(description);
        setAddress(address);
    }

    public Restaurant(Restaurant u) {
        this(u.getId(), u.getName(), u.getDescription(), u.getAddress());
    }

    public Restaurant() {
        super();
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

    @Override
    public String toString() {
        return "Restaurant {" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", address=" + address +
                '}';
    }
}
package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractNamedEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
@Getter @Setter
@NoArgsConstructor
@ToString(callSuper = true)
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
}
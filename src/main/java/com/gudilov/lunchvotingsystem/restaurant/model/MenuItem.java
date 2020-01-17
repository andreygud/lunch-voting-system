package com.gudilov.lunchvotingsystem.restaurant.model;


import com.gudilov.lunchvotingsystem.common.model.AbstractNamedEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_item")
@Getter @Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class MenuItem extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private double price;

    @Column(name = "menu_date")
    private LocalDate menuDate;

}
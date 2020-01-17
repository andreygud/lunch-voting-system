package com.gudilov.lunchvotingsystem.restaurant.to;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MenuItemHistoryTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime menuDate;

    private int restaurantId;
    private String restaurantName;

    private String dishName;
    private double price;
}

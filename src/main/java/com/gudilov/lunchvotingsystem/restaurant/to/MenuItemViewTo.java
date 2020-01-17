package com.gudilov.lunchvotingsystem.restaurant.to;

import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuItemViewTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer restaurantId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private double price;

    @NotNull
    private LocalDate menuDate;

    public MenuItemViewTo(Integer id, Integer restaurantId, String name, double price, LocalDate menuDate) {
        super(id);
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.menuDate = menuDate;
    }
}

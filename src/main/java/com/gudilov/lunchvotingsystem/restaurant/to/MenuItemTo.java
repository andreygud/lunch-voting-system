package com.gudilov.lunchvotingsystem.restaurant.to;

import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuItemTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private double price;

    public MenuItemTo(Integer id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}

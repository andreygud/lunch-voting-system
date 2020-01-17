package com.gudilov.lunchvotingsystem.restaurant.to;

import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 1000)
    private String description;

    @Size(min = 5, max = 255)
    private String address;

    public RestaurantTo(Integer id, String name, String description, String address) {
        super(id);
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public RestaurantTo(RestaurantTo r) {
        this(r.getId(), r.getName(), r.getDescription(), r.getAddress());
    }
}

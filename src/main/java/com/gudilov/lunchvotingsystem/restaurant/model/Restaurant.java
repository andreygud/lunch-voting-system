package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractNamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

import static com.gudilov.lunchvotingsystem.restaurant.model.Restaurant.*;

@Entity
@Table(name = "restaurant")
@Getter @Setter
@NoArgsConstructor
@ToString(callSuper = true)
@FilterDef(name= CURRENT_DAY_FILTER)
public class Restaurant extends AbstractNamedEntity {

    public static final String CURRENT_DAY_FILTER = "currentDay";

    @Column(name = "description")
    @Size(max = 1000)
    private String description;

    @Column(name = "address", nullable = false)
    @Size(min = 5, max = 255)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("menuDate ASC")
    @Filter(name=CURRENT_DAY_FILTER,condition = "menu_date = current_date()")
    private List<MenuItem> todayItems;

    public Restaurant(Integer id, String name, String description, String address) {
        super(id, name);
        setDescription(description);
        setAddress(address);
    }
}
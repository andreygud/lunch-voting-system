package com.gudilov.lunchvotingsystem.common.to;

import com.gudilov.lunchvotingsystem.common.model.HasId;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class BaseTo implements HasId {
    protected Integer id;

    public BaseTo(Integer id) {
        this.id = id;
    }
}

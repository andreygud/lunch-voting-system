package com.gudilov.lunchvotingsystem.restaurant.to;

import com.gudilov.lunchvotingsystem.common.to.BaseTo;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VoteViewTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime voteTime;

    private String userName;

    private Integer userId;

    private String restaurantName;

    private Integer restaurantId;

    public VoteViewTo(Integer id, LocalDateTime voteTime, String userName, Integer userId, String restaurantName, Integer restaurantId) {
        super(id);
        this.voteTime = voteTime;
        this.userName = userName;
        this.userId = userId;
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
    }
}

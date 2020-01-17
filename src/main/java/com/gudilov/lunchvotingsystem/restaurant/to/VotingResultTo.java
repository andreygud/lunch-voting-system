package com.gudilov.lunchvotingsystem.restaurant.to;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class VotingResultTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dateTime;
    private int restaurantId;
    private String restaurantName;
    private int votesCount;
}

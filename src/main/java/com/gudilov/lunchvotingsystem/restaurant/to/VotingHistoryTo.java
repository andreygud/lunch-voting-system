package com.gudilov.lunchvotingsystem.restaurant.to;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class VotingHistoryTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime voteDateTime;

    private int userId;
    private String userName;

    private int restaurantId;
    private String restaurantName;
}

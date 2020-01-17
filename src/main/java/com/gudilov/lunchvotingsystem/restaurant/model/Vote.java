package com.gudilov.lunchvotingsystem.restaurant.model;

import com.gudilov.lunchvotingsystem.common.model.AbstractBaseEntity;
import com.gudilov.lunchvotingsystem.user.model.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@Getter @Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;

    @Column(name = "vote_time", nullable = false)
    @NotNull
    private LocalDateTime voteTime;

    public Vote(Integer id) {
        super(id);
    }

    public Vote(LocalDateTime voteTime) {
        super(null);
        this.voteTime = voteTime;
    }
}
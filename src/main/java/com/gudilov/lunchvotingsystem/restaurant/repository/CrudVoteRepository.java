package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v from Vote v WHERE v.voteTime >= :start AND v.voteTime < :end and v.user.id = :userId")
    List<Vote> getAllBetweenByUserId(@Param("userId") int userId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Vote getByIdAndUserId(int id, int userId);
}

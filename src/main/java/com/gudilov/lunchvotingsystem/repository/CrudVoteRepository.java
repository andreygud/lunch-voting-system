package com.gudilov.lunchvotingsystem.repository;


import com.gudilov.lunchvotingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("select v from Vote v where v.voteDate >= :startDate")
    List<Vote> getAllAfterDateTime(@Param("startDate") LocalDate startDate);

    @Query("select v from Vote v where v.voteDate >= :startDate and v.userId = :userId")
    List<Vote> getAllForDateAndUser(@Param("startDate") LocalDate startDate, @Param("userId") int userId);

}

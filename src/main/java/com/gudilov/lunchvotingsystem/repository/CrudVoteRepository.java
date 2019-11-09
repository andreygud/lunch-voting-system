package com.gudilov.lunchvotingsystem.repository;


import com.gudilov.lunchvotingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("select v from Vote v where v.votingtime >= :startDateTime")
    List<Vote> getAllAfterDateTime(@Param("startDateTime") LocalDateTime startDateTime);

}

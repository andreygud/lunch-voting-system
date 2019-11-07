package com.gudilov.lunchvotingsystem.repository.mock;

import com.gudilov.lunchvotingsystem.model.Vote;
import com.gudilov.lunchvotingsystem.repository.VoteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteMockRepository extends GrantSeparatedMockRepository<Vote> implements VoteRepository {

}

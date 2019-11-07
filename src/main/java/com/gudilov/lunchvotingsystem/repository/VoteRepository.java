package com.gudilov.lunchvotingsystem.repository;


import com.gudilov.lunchvotingsystem.model.Vote;

import java.util.List;

public interface VoteRepository {

    public List<Vote> getAll(int userId);

    public Vote save(Vote vote, int userId);
}

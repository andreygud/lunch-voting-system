package com.gudilov.lunchvotingsystem.web;

import com.gudilov.lunchvotingsystem.dto.VoteResult;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VoteController {

    public List<VoteResult> getVotingResults() {
        return new ArrayList<>();
    }

    public void vote(int restaurantId) {
        throw new IllegalArgumentException();
    }
}

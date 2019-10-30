package com.gudilov.lunchvotingsystem;

import com.gudilov.lunchvotingsystem.dto.VoteResult;

import java.time.LocalDateTime;
import java.util.*;

import com.gudilov.lunchvotingsystem.CommonTestData;
import com.gudilov.lunchvotingsystem.model.Vote;

public class VoteTestData extends CommonTestData {

    public static final int RESTAURANT_ID1 = 1;

    public static final VoteResult VOTE_RESULT1 = new VoteResult(RESTAURANT_ID1, "Original Joes", 1L);
    public static final VoteResult VOTE_RESULT2 = new VoteResult(RESTAURANT_ID1 + 1, "Cactus Club Cafe", 1L);
    public static final VoteResult VOTE_RESULT3 = new VoteResult(RESTAURANT_ID1 + 2, "Earls", 1L);

    public static final List<VoteResult> VOTE_RESULTS_SORTED = Arrays.asList(VOTE_RESULT1, VOTE_RESULT2, VOTE_RESULT3);

    public static final Vote VOTE1 = new Vote(1, RESTAURANT_ID1, LocalDateTime.of(2019, 10, 10, 10, 0), 1);
    public static final Vote VOTE2 = new Vote(2, RESTAURANT_ID1 + 1, LocalDateTime.of(2019, 10, 10, 11, 0), 2);
    public static final Vote VOTE3 = new Vote(3, RESTAURANT_ID1 + 2, LocalDateTime.of(2019, 10, 10, 11, 0), 3);
    public static final Vote VOTE4 = new Vote(3, RESTAURANT_ID1 + 2, LocalDateTime.of(2019, 10, 10, 10, 30), 1);

    public static final List<Vote> VOTES = Arrays.asList(VOTE1, VOTE2, VOTE3,VOTE4);


    public static final Map<Integer, Long> RESTAURANT_SUMMARY = Map.ofEntries(
            new AbstractMap.SimpleEntry<Integer, Long>(RESTAURANT_ID1,1L),
            new AbstractMap.SimpleEntry<Integer, Long>(RESTAURANT_ID1+1,1L),
            new AbstractMap.SimpleEntry<Integer, Long>(RESTAURANT_ID1+2,2L)
    );
}

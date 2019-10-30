package com.gudilov.lunchvotingsystem.web;

import com.gudilov.lunchvotingsystem.dto.VoteResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.gudilov.lunchvotingsystem.CommonTestData;

public class VoteTestData extends CommonTestData {

    public static final int RESTAURANT_ID1 = 1;

    public static final VoteResult VOTE_RESULT1 = new VoteResult(RESTAURANT_ID1,"Original Joes", LocalDateTime.of(2019,10,5,9,23),1);
    public static final VoteResult VOTE_RESULT2 = new VoteResult(RESTAURANT_ID1+1,"Cactus Club Cafe", LocalDateTime.of(2019,10,5,9,23),1);
    public static final VoteResult VOTE_RESULT3 = new VoteResult(RESTAURANT_ID1+2,"Earls", LocalDateTime.of(2019,10,5,9,23),1);

    public static final List<VoteResult> VOTES_SORTED = Arrays.asList(VOTE_RESULT1, VOTE_RESULT2, VOTE_RESULT3);
}

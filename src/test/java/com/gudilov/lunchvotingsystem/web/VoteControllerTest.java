package com.gudilov.lunchvotingsystem.web;

import com.gudilov.lunchvotingsystem.dto.VoteResult;
import com.gudilov.lunchvotingsystem.exceptions.BusinessRuleViolationException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;
import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class VoteControllerTest {

    @Autowired
    VoteController voteController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getVotingResults_sortByRestaurantName_success() {
        List<VoteResult> votes = voteController.getVotingResults();
        Assertions.assertThat(votes).usingFieldByFieldElementComparator().isEqualTo(VoteTestData.VOTES_SORTED);
    }

    @Test
    public void vote_1stVote_success() {
        voteController.vote(VoteTestData.RESTAURANT_ID1);
        VoteResult result = voteController.getVotingResults().stream()
                .filter( voteResult -> Objects.equals(voteResult.getRestaurantID(),VoteTestData.RESTAURANT_ID1))
                .findFirst().orElse(null);

        assertEquals(1,result.getVotesNumber());
    }

    @Test(expected = BusinessRuleViolationException.class)
    public void vote_1stVoteAfter1100_fail() {
        //todo: setTime to 11:00
        voteController.vote(VoteTestData.RESTAURANT_ID1);
    }

    @Test
    public void vote_2ndVoteRestaurantChangeBefore1100_success() {

        int before = voteController.getVotingResults().stream().mapToInt(VoteResult::getVotesNumber).sum();

        //todo: setTime to 9:00
        voteController.vote(VoteTestData.RESTAURANT_ID1);
        //todo: setTime to 10:00
        voteController.vote(VoteTestData.RESTAURANT_ID1 + 1);

        //check total number of votes stays the same, vote moves from one restaurant to another.
        int after = voteController.getVotingResults().stream().mapToInt(VoteResult::getVotesNumber).sum();
        assertEquals(before,after);
    }

    @Test(expected = BusinessRuleViolationException.class)
    public void vote_2ndVoteAfter1100_fail() {

        //todo: setTime to 9:00
        voteController.vote(VoteTestData.RESTAURANT_ID1);
        //todo: setTime to 11:30
        voteController.vote(VoteTestData.RESTAURANT_ID1 + 1);
    }
}
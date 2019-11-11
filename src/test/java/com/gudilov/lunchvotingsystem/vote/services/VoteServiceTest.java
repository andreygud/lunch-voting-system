package com.gudilov.lunchvotingsystem.vote.services;

import com.gudilov.lunchvotingsystem.CommonTestData;
import com.gudilov.lunchvotingsystem.UserTestData;
import com.gudilov.lunchvotingsystem.VoteTestData;
import com.gudilov.lunchvotingsystem.vote.exceptions.BusinessRuleViolationException;
import com.gudilov.lunchvotingsystem.vote.services.mock.ShiftedDateAndTimeService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static com.gudilov.lunchvotingsystem.CommonTestData.CLOCK_TODAY_BEFORE_1100;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mock-rep.xml"
})
@RunWith(SpringRunner.class)
@Sql("/db/populateDbWithTestData.sql")
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Autowired
    private ShiftedDateAndTimeService dateAndTimeService;

    @Before
    public void setUp(){
        //setTime for everyone
        dateAndTimeService.setClock(CLOCK_TODAY_BEFORE_1100);
    }

    @Test
    public void getAllToday(){
        Map<String,Integer> today_result = voteService.getTodayResults();
        Assertions.assertThat(today_result).containsAllEntriesOf(VoteTestData.INITIAL_VOTES);
    }

    @Test
    public void vote_newVoteBefore1100() {
        int before = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER7);
        int after = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);

        Assert.assertEquals(before+1,after);
    }

    @Test(expected = BusinessRuleViolationException.class)
    public void vote_noNewVoteAfter1100() {
        dateAndTimeService.setClock(CommonTestData.CLOCK_TODAY_AFTER_1100);

        int before = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER7);
        int after = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);

        Assert.assertEquals(before+1,after);
    }

    @Test(expected = BusinessRuleViolationException.class)
    public void vote_onlyOneVotePerUserPerDay_NoChangeAfter1100() {
        dateAndTimeService.setClock(CommonTestData.CLOCK_TODAY_AFTER_1100);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER1);
    }

    @Test
    public void vote_onlyOneVotePerUserPerDay_canChangeRestaurantBefore1100(){
        int firstRestaurantBefore = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER7);
        int firstRestaurantAfter = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);

        Assert.assertEquals(firstRestaurantBefore+1,firstRestaurantAfter);

        int secondRestaurantBefore = voteService.getTodayResults().get(VoteTestData.RESTAURANT_CACTUS);
        voteService.vote(VoteTestData.RESTAURANT_CACTUS,UserTestData.USER7);
        int secondRestaurantAfter = voteService.getTodayResults().get(VoteTestData.RESTAURANT_CACTUS);
        int firstRestaurantAfterSecond = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);

        Assert.assertEquals(secondRestaurantBefore+1, secondRestaurantAfter);
        Assert.assertEquals(firstRestaurantBefore, firstRestaurantAfterSecond);
    }

    @Test
    public void vote_shiftTimeAndVote(){
        dateAndTimeService.setClock(CommonTestData.CLOCK_TOMMOROW_BEFORE_1100);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER1);
    }

}
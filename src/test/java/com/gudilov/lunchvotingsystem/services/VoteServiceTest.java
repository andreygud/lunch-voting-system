package com.gudilov.lunchvotingsystem.services;

import com.gudilov.lunchvotingsystem.CommonTestData;
import com.gudilov.lunchvotingsystem.UserTestData;
import com.gudilov.lunchvotingsystem.VoteTestData;
import com.gudilov.lunchvotingsystem.exceptions.BusinessRuleViolationException;
import com.gudilov.lunchvotingsystem.services.mock.ShiftedDateAndTimeService;
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
    public void vote() {
        int before = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER7);
        int after = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);

        Assert.assertEquals(before+1,after);
    }

    @Test(expected = BusinessRuleViolationException.class)
    public void vote_onlyOneVotePerUserPerDay() {
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER1);
    }

    @Test
    public void vote_shiftTimeAndVote(){
        dateAndTimeService.setClock(CommonTestData.CLOCK_TOMMOROW_BEFORE_1100);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER1);
    }

}
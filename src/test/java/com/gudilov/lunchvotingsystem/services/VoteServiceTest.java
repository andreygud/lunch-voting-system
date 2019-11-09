package com.gudilov.lunchvotingsystem.services;

import com.gudilov.lunchvotingsystem.UserTestData;
import com.gudilov.lunchvotingsystem.VoteTestData;
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


@ContextConfiguration({
        "classpath:spring/spring-app.xml"
})
@RunWith(SpringRunner.class)
@Sql("/db/populateDB.sql")
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Before
    public void setUp(){
    }

    @Test
    public void getAllToday(){
        Map<String,Integer> today_result = voteService.getTodayResults();
        Assertions.assertThat(today_result).containsAllEntriesOf(VoteTestData.INITIAL_VOTES);
    }

    @Test
    public void vote() {
        int before = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);
        voteService.vote(VoteTestData.RESTAURANT_JOES,UserTestData.USER5);
        int after = voteService.getTodayResults().get(VoteTestData.RESTAURANT_JOES);

        Assert.assertEquals(before+1,after);
    }

}
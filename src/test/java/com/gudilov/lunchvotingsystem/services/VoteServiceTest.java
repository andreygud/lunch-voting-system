package com.gudilov.lunchvotingsystem.services;

import com.gudilov.lunchvotingsystem.UserTestData;
import com.gudilov.lunchvotingsystem.VoteTestData;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mock-rep.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void vote() {
        throw new IllegalArgumentException();
    }

    @Test
    public void getTodaysSummaryByRestaurant() {
        Map<Integer, Long> map = voteService.getTodaysSummaryByRestaurant(UserTestData.USER1);
        Assertions.assertThat(map).containsAllEntriesOf(VoteTestData.RESTAURANT_SUMMARY);
    }
}
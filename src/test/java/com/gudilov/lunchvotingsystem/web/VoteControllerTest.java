package com.gudilov.lunchvotingsystem.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void getVotingResults() {
        voteController.getVotingResults();

    }

    @Test
    public void vote() {
    }
}
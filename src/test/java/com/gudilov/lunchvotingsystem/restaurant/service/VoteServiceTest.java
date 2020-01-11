package com.gudilov.lunchvotingsystem.restaurant.service;

import com.gudilov.lunchvotingsystem.common.exceptions.BusinessRuleViolationException;
import com.gudilov.lunchvotingsystem.common.exceptions.NotFoundException;
import com.gudilov.lunchvotingsystem.restaurant.model.Vote;
import com.gudilov.lunchvotingsystem.restaurant.repository.vote.VoteRepository;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;

import static com.gudilov.lunchvotingsystem.common.utils.TestUtil.shiftTime;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData.RESTAURANT3_ID;
import static com.gudilov.lunchvotingsystem.restaurant.VoteTestData.*;
import static com.gudilov.lunchvotingsystem.user.UserTestData.ADMIN_ID;
import static com.gudilov.lunchvotingsystem.user.UserTestData.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteServiceTest {

    @Autowired
    VoteService voteService;
    @Autowired
    VoteRepository voteRepository;

    @Test
    void vote_updateVote_before1100() {
        shiftTime(DATE_TIME_BEFORE1100);

        voteService.vote(ADMIN_ID, RESTAURANT1_ID);
        assertEquals(2, voteRepository.getAll().size());
    }

    @Test
    void vote_updateVote_after1100() {
        shiftTime(DATE_TIME_AFTER1100);

        assertThrows(BusinessRuleViolationException.class, () -> voteService.vote(ADMIN_ID, RESTAURANT3_ID));
        assertEquals(2, voteRepository.getAll().size());
    }

    @Test
    void vote_nextday_before1100() {
        shiftTime(NEXTDAY_DATE_TIME_BEFORE1100);

        voteService.vote(ADMIN_ID, RESTAURANT1_ID);
        assertEquals(3, voteRepository.getAll().size());
    }

    @Test
    void vote_duplication() {
        shiftTime(DATE_TIME_BEFORE1100);

        Vote duplication = new Vote(LocalDateTime.now().minusMinutes(2));
        voteRepository.save(duplication, ADMIN_ID, RESTAURANT1_ID);

        assertThrows(IllegalStateException.class, () -> voteService.vote(ADMIN_ID, RESTAURANT3_ID));
    }

    @Test
    void vote_nextday_before1100_the_same_restaurant() {
        shiftTime(NEXTDAY_DATE_TIME_BEFORE1100);

        voteService.vote(ADMIN_ID, RESTAURANT1_ID);
        voteService.vote(USER_ID, RESTAURANT1_ID);
        assertEquals(4, voteRepository.getAll().size());
    }
    //todo check wrong restaurant and user
    @Test
    void getLast() {
        shiftTime(DATE_TIME_BEFORE1100);
        VoteViewTo actual1 = voteService.getLast(USER_ID);
        assertEquals(INITIAL_USER_VOTE_TO,actual1);

        shiftTime(NEXTDAY_DATE_TIME_BEFORE1100);
        voteService.vote(USER_ID,RESTAURANT3_ID);

        VoteViewTo actual2 = voteService.getLast(USER_ID);
        assertEquals(SUCCESS_VOTE_TO,actual2);
    }

    @Test
    void get() {
        VoteViewTo actual1 = voteService.get(USER_ID,INITIAL_USER_VOTE_TO.getId());
        assertEquals(INITIAL_USER_VOTE_TO,actual1);
    }

    @Test
    void get_wrongUser() {
        assertThrows(NotFoundException.class,()->voteService.get(ADMIN_ID,INITIAL_USER_VOTE_TO.getId()));
    }
}
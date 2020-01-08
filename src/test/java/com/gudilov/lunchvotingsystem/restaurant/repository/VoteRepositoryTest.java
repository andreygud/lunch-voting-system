package com.gudilov.lunchvotingsystem.restaurant.repository;

import com.gudilov.lunchvotingsystem.restaurant.RestaurantTestData;
import com.gudilov.lunchvotingsystem.restaurant.model.Vote;
import com.gudilov.lunchvotingsystem.user.UserTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//todo remove this testing when Service Layer and WebLayer unit tests are ready
class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void save() {


        Vote vote = new Vote();
        vote.setId(100005);
        vote.setVoteTime(LocalDateTime.now().plusHours(10L));
        System.out.println("!!!!!!!" + vote);
        System.out.println("!!!!!!!" + voteRepository.save(vote, UserTestData.USER_ID, RestaurantTestData.RESTAURANT3_ID));
    }

    @Test
    void getAllBetweenByUserId() {
        List<Vote> votes = voteRepository.getAllBetweenByUserId(UserTestData.USER_ID, LocalDateTime.of(LocalDate.now(), LocalTime.MIN), LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
        System.out.println("!!!!!!!" + votes);
    }
}
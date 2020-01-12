package com.gudilov.lunchvotingsystem.restaurant.reports;

import com.gudilov.lunchvotingsystem.restaurant.to.VotingHistoryTo;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingResultTo;
import com.gudilov.lunchvotingsystem.user.UserTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class ReportingRepositoryTest {
    @Autowired
    private ReportingRepository repository;

    @Test
    void getVotingResults() {
        List<VotingResultTo> list = repository.getVotingResults(LocalDate.now());
        System.out.println(list);
    }

    @Test
    void getVotingHistory() {
        List<VotingHistoryTo> history = repository.getVotingHistory(UserTestData.USER_ID, LocalDate.now().minusDays(2));
        System.out.println(history);
    }
}
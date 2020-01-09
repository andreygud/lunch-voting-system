package com.gudilov.lunchvotingsystem.restaurant.reports;

import com.gudilov.lunchvotingsystem.restaurant.to.VotingResultTo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ReportingRepository {

    public static final BeanPropertyRowMapper<VotingResultTo> MAPPER = BeanPropertyRowMapper.newInstance(VotingResultTo.class);

    private final JdbcTemplate jdbcTemplate;

    public ReportingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VotingResultTo> getVotingResults(LocalDate day) {
        return jdbcTemplate.query("select current_timestamp() as dateTime, r.ID as restaurantId, " +
                        "r.NAME as restaurantName, count(1) as votesCount " +
                        "from VOTE v join RESTAURANT r on v.RESTAURANT_ID = r.ID " +
                        "where FORMATDATETIME(v.VOTE_TIME, 'YYYY-MM-dd') = ? group by r.ID, r.NAME"
                , MAPPER, day);
    }
}

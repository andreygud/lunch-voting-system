package com.gudilov.lunchvotingsystem.restaurant.reports;

import com.gudilov.lunchvotingsystem.restaurant.to.MenuItemHistoryTo;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingHistoryTo;
import com.gudilov.lunchvotingsystem.restaurant.to.VotingResultTo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ReportingRepository {

    public static final BeanPropertyRowMapper<VotingResultTo> VOTING_RESULT_MAPPER = BeanPropertyRowMapper.newInstance(VotingResultTo.class);
    public static final BeanPropertyRowMapper<VotingHistoryTo> VOTING_HISTORY_MAPPER = BeanPropertyRowMapper.newInstance(VotingHistoryTo.class);
    public static final BeanPropertyRowMapper<MenuItemHistoryTo> MENU_HISTORY_MAPPER = BeanPropertyRowMapper.newInstance(MenuItemHistoryTo.class);

    private final JdbcTemplate jdbcTemplate;

    public ReportingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VotingResultTo> getVotingResults(LocalDate day) {
        return jdbcTemplate.query("select current_timestamp() as dateTime, r.ID as restaurantId, " +
                        "r.NAME as restaurantName, count(1) as votesCount " +
                        "from VOTE v join RESTAURANT r on v.RESTAURANT_ID = r.ID " +
                        "where FORMATDATETIME(v.VOTE_TIME, 'YYYY-MM-dd') = ? group by r.ID, r.NAME"
                , VOTING_RESULT_MAPPER, day);
    }

    public List<VotingHistoryTo> getVotingHistory(int userId, LocalDate startFromDay) {
        return jdbcTemplate.query("select v.VOTE_TIME as voteDateTime, r.ID as restaurantId, r.NAME as restaurantName, " +
                        " u.id as userId, u.NAME as userName " +
                        " from VOTE v join RESTAURANT r on v.RESTAURANT_ID = r.ID join USERS u on v.USER_ID = u.ID " +
                        " where v.USER_ID = ? and v.VOTE_TIME > ? order by v.VOTE_TIME",
                VOTING_HISTORY_MAPPER,
                userId, LocalDateTime.of(startFromDay, LocalTime.MIN));
    }

    public List<MenuItemHistoryTo> getMenuHistory(LocalDate startFromDay, Integer restaurantId) {
        return jdbcTemplate.query("select mi.MENU_DATE as menuDate, r.id as restaurantId, r.NAME as restaurantName, " +
                        "mi.NAME as dishName, mi.PRICE as price  " +
                        "from MENU_ITEM mi join RESTAURANT R on mi.RESTAURANT_ID = R.ID where mi.MENU_DATE >= ? and r.id = NVL(?,r.id) order by menuDate,restaurantId,dishName",
                MENU_HISTORY_MAPPER,
                LocalDateTime.of(startFromDay, LocalTime.MIN),restaurantId);
    }
}


package com.gudilov.lunchvotingsystem.restaurant;

import com.gudilov.lunchvotingsystem.common.TestMatchers;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.gudilov.lunchvotingsystem.user.UserTestData.USER;

public class VoteTestData {

    public static final int USER_CREATED_VOTE_ID = 100007;
    public static final int USER_UPDATE_VOTE_ID = 100005;

    public static final LocalTime TIME_BEFORE1100 = LocalTime.of(10, 15);
    public static final LocalTime TIME_AFTER1100 = LocalTime.of(11, 15);
    public static final LocalDateTime DATE_TIME_BEFORE1100 = LocalDateTime.of(LocalDate.now(), TIME_BEFORE1100);
    public static final LocalDateTime DATE_TIME_AFTER1100 = LocalDateTime.of(LocalDate.now(), TIME_AFTER1100);
    public static final LocalDateTime NEXTDAY_DATE_TIME_BEFORE1100 = LocalDateTime.of(LocalDate.now().plusDays(1), TIME_BEFORE1100);

    public static final VoteViewTo SUCCESS_VOTE_TO = new VoteViewTo(USER_CREATED_VOTE_ID, NEXTDAY_DATE_TIME_BEFORE1100, USER.getName(), USER.getId(), RestaurantTestData.RESTAURANT3_TO.getName(), RestaurantTestData.RESTAURANT3_ID);
    public static final VoteViewTo SUCCESS_UPDATE_VOTE_TO = new VoteViewTo(USER_UPDATE_VOTE_ID, DATE_TIME_BEFORE1100, USER.getName(), USER.getId(), RestaurantTestData.RESTAURANT3_TO.getName(), RestaurantTestData.RESTAURANT3_ID);
    public static final String RULE_VIOLATION_JSON = "{\"errorMessage\":\"BusinessRuleViolationException\",\"details\":[\"You cannot vote after 11:00\"]}";

    public static final TestMatchers<VoteViewTo> VOTE_VIEW_TO_TEST_MATCHERS = TestMatchers.useFieldsComparator(VoteViewTo.class);
}

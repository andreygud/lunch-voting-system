package com.gudilov.lunchvotingsystem.restaurant;

import com.gudilov.lunchvotingsystem.common.TestMatchers;
import com.gudilov.lunchvotingsystem.restaurant.to.VoteViewTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.gudilov.lunchvotingsystem.user.UserTestData.ADMIN;
import static com.gudilov.lunchvotingsystem.user.UserTestData.USER;

public class VoteTestData {

    public static final int USER_CREATED_VOTE_ID = 100015;
    public static final int USER_UPDATE_VOTE_ID = 100005;

    public static final LocalTime INITIAL_USER_VOTE_TIME = LocalTime.of(9, 47,52,690000000);
    public static final LocalTime INITIAL_ADMIN_VOTE_TIME = LocalTime.of(10, 14,30,360000000);
    public static final LocalTime TIME_BEFORE1100 = LocalTime.of(10, 15);
    public static final LocalTime TIME_AFTER1100 = LocalTime.of(11, 15);
    public static final LocalDateTime DATE_TIME_BEFORE1100 = LocalDateTime.of(LocalDate.now(), TIME_BEFORE1100);
    public static final LocalDateTime DATE_TIME_AFTER1100 = LocalDateTime.of(LocalDate.now(), TIME_AFTER1100);
    public static final LocalDateTime INITIAL_USER_DATE_TIME = LocalDateTime.of(LocalDate.now(), INITIAL_USER_VOTE_TIME);
    public static final LocalDateTime INITIAL_ADMIN_DATE_TIME = LocalDateTime.of(LocalDate.now(), INITIAL_ADMIN_VOTE_TIME);
    public static final LocalDateTime NEXTDAY_DATE_TIME_BEFORE1100 = LocalDateTime.of(LocalDate.now().plusDays(1), TIME_BEFORE1100);
    public static final LocalDateTime PREVDAY_DATE_TIME_BEFORE1100 = LocalDateTime.of(LocalDate.now().minusDays(1), TIME_BEFORE1100);

    public static final VoteViewTo SUCCESS_VOTE_TO = new VoteViewTo(USER_CREATED_VOTE_ID, NEXTDAY_DATE_TIME_BEFORE1100, USER.getName(), USER.getId(), RestaurantTestData.RESTAURANT3_TO.getName(), RestaurantTestData.RESTAURANT3_ID);
    public static final VoteViewTo SUCCESS_UPDATE_VOTE_TO = new VoteViewTo(USER_UPDATE_VOTE_ID, DATE_TIME_BEFORE1100, USER.getName(), USER.getId(), RestaurantTestData.RESTAURANT3_TO.getName(), RestaurantTestData.RESTAURANT3_ID);
    public static final String RULE_VIOLATION_JSON = "{\"errorMessage\":\"BusinessRuleViolationException\",\"details\":[\"You cannot vote after 11:00\"]}";

    public static final VoteViewTo INITIAL_USER_VOTE_TO = new VoteViewTo(USER_UPDATE_VOTE_ID,INITIAL_USER_DATE_TIME,USER.getName(),USER.getId(),RestaurantTestData.RESTAURANT1_TO.getName(),RestaurantTestData.RESTAURANT1_ID);
    public static final VoteViewTo INITIAL_ADMIN_VOTE_TO = new VoteViewTo(USER_UPDATE_VOTE_ID+1,INITIAL_ADMIN_DATE_TIME,ADMIN.getName(),ADMIN.getId(),RestaurantTestData.RESTAURANT2_TO.getName(),RestaurantTestData.RESTAURANT2_ID);
    public static final String NOTFOUND_VOTE_JSON = "{\"errorMessage\":\"NotFoundException\",\"details\":[\"You don't have such vote, or it doesn't belong to you.\"]}";

    public static final TestMatchers<VoteViewTo> VOTE_VIEW_TO_TEST_MATCHERS = TestMatchers.useFieldsComparator(VoteViewTo.class);
    public static final String VOTING_RESULTS_JSON = "[{\"restaurantId\":100002,\"restaurantName\":\"Cactus Club Cafe Kingsway\",\"votesCount\":1},{\"restaurantId\":100003,\"restaurantName\":\"Original Joe's Restaurant & Bar\",\"votesCount\":1}]";
    public static final String NOT_FOUND_RESTAURANT_JSON = "{\"url\":\"http://localhost/rest/vote/100012\",\"errorMessage\":\"EntityNotFoundException\",\"details\":[\"Unable to find com.gudilov.lunchvotingsystem.restaurant.model.Restaurant with id 100012\"]}";
}

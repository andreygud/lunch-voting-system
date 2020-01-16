package com.gudilov.lunchvotingsystem.restaurant;

import com.gudilov.lunchvotingsystem.common.TestMatchers;
import com.gudilov.lunchvotingsystem.restaurant.to.RestaurantTo;

import java.util.Set;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = 100002;
    public static final RestaurantTo RESTAURANT1_TO = new RestaurantTo(RESTAURANT1_ID, "Cactus Club Cafe Kingsway", "Known for good cocktails", "4653 Kingsway, Burnaby, BC");

    public static final int RESTAURANT2_ID = 100003;
    public static final RestaurantTo RESTAURANT2_TO = new RestaurantTo(RESTAURANT2_ID, "Original Joe's Restaurant & Bar", "Workdays they open 11.00am", "298 Robson St, Vancouver, BC");

    public static final int RESTAURANT3_ID = 100004;
    public static final RestaurantTo RESTAURANT3_TO = new RestaurantTo(RESTAURANT3_ID, "Hi Genki Restaurant", "Sushi", "6680 Southoaks Crescent, Burnaby");


    public static final int RESTAURANT_CREATED_ID = 100026;
    public static final RestaurantTo RESTAURANT_CREATE_TO = new RestaurantTo(null, "Sams Kitchen", "just simple food", "behind the yellow building");
    public static final RestaurantTo RESTAURANT_WRONG_ALL_INPUT_CREATE_TO = new RestaurantTo(null, "S", null, "beh");
    public static final RestaurantTo RESTAURANT_CREATED_TO = new RestaurantTo(RESTAURANT_CREATED_ID, "Sams Kitchen", "just simple food", "behind the yellow building");
    public static final Set<String> RESTAURANT_CREATION_VIOLATIONS = Set.of("size must be between 2 and 100", "size must be between 5 and 255");
    public static final String WRONG_INPUT_JSON = "{\"errorMessage\":\"MethodArgumentNotValidException\",\"details\":[\"name has wrong value: size must be between 2 and 100\",\"address has wrong value: size must be between 5 and 255\"]}";

    public static final TestMatchers<RestaurantTo> RESTAURANT_TO_TEST_MATCHERS = TestMatchers.useFieldsComparator(RestaurantTo.class);

}

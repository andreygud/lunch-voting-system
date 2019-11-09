package com.gudilov.lunchvotingsystem;

import java.util.Map;

public class VoteTestData extends CommonTestData {

    public static final String RESTAURANT_JOES = "Joes";
    public static final String RESTAURANT_CACTUS = "Cactus";

    public static final Map<String, Integer> INITIAL_VOTES = Map.of(
            RESTAURANT_JOES,2,
            RESTAURANT_CACTUS,3
    );
}

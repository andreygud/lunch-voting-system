package com.gudilov.lunchvotingsystem;

import java.time.*;

public class CommonTestData {

    public static final ZoneId ZONE_ID = ZoneId.systemDefault();
    public static final ZoneOffset ZONE_OFFSET = ZONE_ID.getRules().getOffset(LocalDateTime.now());

    public static final LocalDateTime TODAY_BEFORE_1100 = LocalDateTime.of(2019, 11, 7, 10, 45);
    public static final LocalDateTime TODAY_AFTER_1100 = LocalDateTime.of(2019, 11, 7, 12, 35);
    public static final LocalDateTime TOMMOROW = LocalDateTime.of(2019, 11, 8, 10, 35);



    public static final Clock CLOCK_TODAY_BEFORE_1100 = Clock.fixed(CommonTestData.TODAY_BEFORE_1100.toInstant(ZONE_OFFSET), ZONE_ID);
    public static final Clock CLOCK_TODAY_AFTER_1100 = Clock.fixed(CommonTestData.TODAY_AFTER_1100.toInstant(ZONE_OFFSET), ZONE_ID); ;
    public static final Clock CLOCK_TOMMOROW_BEFORE_1100 = Clock.fixed(CommonTestData.TOMMOROW.toInstant(ZONE_OFFSET), ZONE_ID);


}

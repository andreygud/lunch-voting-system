package com.gudilov.lunchvotingsystem;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class CommonTestData {

    public static final LocalDateTime TODAY_BEFORE_1100 = LocalDateTime.of(2019, 11, 9, 10, 45);
    public static final LocalDateTime TODAY_AFTER_1100 = LocalDateTime.of(2019, 11, 9, 12, 35);
    public static final LocalDateTime TOMMOROW = LocalDateTime.of(2019, 11, 10, 10, 35);

    public static final Clock CLOCK_TODAY_BEFORE_1100 = Clock.fixed(CommonTestData.TODAY_BEFORE_1100.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));
    public static final Clock CLOCK_TOMMOROW_BEFORE_1100 = Clock.fixed(CommonTestData.TOMMOROW.toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));



}

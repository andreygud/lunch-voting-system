package com.gudilov.lunchvotingsystem.services;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class DateAndTimeService {

    protected Clock clock = Clock.systemUTC();

    public DateAndTimeService() {
    }

    public LocalDateTime startOfToday() {
        return startOfDay(LocalDate.now(clock));
    }

    public LocalDateTime startOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
}

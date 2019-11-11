package com.gudilov.lunchvotingsystem.vote.services;

import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class DateAndTimeService {

    protected Clock clock = Clock.systemUTC();

    public DateAndTimeService() {
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now(clock);
    }

    public LocalTime currentTime() {
        return LocalTime.ofInstant(clock.instant(), ZoneId.systemDefault());
    }

}

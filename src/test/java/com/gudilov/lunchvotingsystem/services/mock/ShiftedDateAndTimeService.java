package com.gudilov.lunchvotingsystem.services.mock;

import com.gudilov.lunchvotingsystem.services.DateAndTimeService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
@Primary
public class ShiftedDateAndTimeService extends DateAndTimeService {

    public void setClock(Clock clock){
        this.clock = clock;
    }
}

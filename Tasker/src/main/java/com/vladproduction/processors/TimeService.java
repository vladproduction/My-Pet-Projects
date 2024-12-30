package com.vladproduction.processors;

import java.time.Duration;
import java.time.Instant;

public class TimeService {
    public static long calculateAmountOfSeconds(Instant startDate){
        Instant now = Instant.now();
        Duration duration = Duration.between(now, startDate);
        return duration.getSeconds();
    }
}

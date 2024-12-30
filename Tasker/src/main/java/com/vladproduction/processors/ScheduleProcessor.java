package com.vladproduction.processors;

import com.vladproduction.tasks.ScheduleTask;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class ScheduleProcessor extends TaskProcessor {
    public void process(ScheduleTask scheduleTask){
        Instant startDate = scheduleTask.getStartDate();
        long delay = TimeService.calculateAmountOfSeconds(startDate);
        executorService.schedule(scheduleTask.getAction(), delay, TimeUnit.SECONDS);
    }
}

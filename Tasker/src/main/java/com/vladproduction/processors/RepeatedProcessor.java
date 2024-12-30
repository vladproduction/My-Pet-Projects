package com.vladproduction.processors;

import com.vladproduction.tasks.RepeatedTask;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class RepeatedProcessor extends TaskProcessor{
    public void process(RepeatedTask repeatedTask){
        Instant startDate = repeatedTask.getStartDate();
        long delay = TimeService.calculateAmountOfSeconds(startDate);
        long nextRun = repeatedTask.getNextRunInSeconds();
        executorService.scheduleAtFixedRate(repeatedTask.getAction(), delay, nextRun, TimeUnit.SECONDS);
    }
}

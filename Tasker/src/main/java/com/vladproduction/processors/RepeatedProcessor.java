package com.vladproduction.processors;

import com.vladproduction.exceptions.TaskProcessingException;
import com.vladproduction.tasks.RepeatedTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RepeatedProcessor extends TaskProcessor{
    private static final Logger logger = LoggerFactory.getLogger(RepeatedProcessor.class);
    public ScheduledFuture<?> process(RepeatedTask repeatedTask){
        Instant startDate = repeatedTask.getStartDate();
        long delay = TimeService.calculateAmountOfSeconds(startDate);
        long nextRun = repeatedTask.getNextRunInSeconds();
        return executorService.scheduleAtFixedRate(() -> {
            try {
                repeatedTask.getAction().run();
            } catch (Exception e) {
                logger.error("Error executing RepeatedTask: {}", e.getMessage(), e);
                throw new TaskProcessingException("RepeatedTask execution failed", e);
            }
        }, delay, nextRun, TimeUnit.SECONDS);
    }
}

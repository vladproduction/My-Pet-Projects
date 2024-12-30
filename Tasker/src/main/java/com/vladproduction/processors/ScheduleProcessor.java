package com.vladproduction.processors;

import com.vladproduction.exceptions.TaskProcessingException;
import com.vladproduction.tasks.ScheduleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleProcessor extends TaskProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleProcessor.class);

    public ScheduledFuture<?> process(ScheduleTask scheduleTask){
        Instant startDate = scheduleTask.getStartDate();
        long delay = TimeService.calculateAmountOfSeconds(startDate);
        return executorService.schedule(() -> {
            try {
                scheduleTask.getAction().run();
            } catch (Exception e) {
                logger.error("Error executing ScheduleTask: {}", e.getMessage(), e);
                throw new TaskProcessingException("ScheduleTask execution failed", e);
            }
        }, delay, TimeUnit.SECONDS);
    }
}

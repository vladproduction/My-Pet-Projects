package com.vladproduction.processors;

import com.vladproduction.exceptions.TaskProcessingException;
import com.vladproduction.tasks.DelayTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;

public class DelayProcessor extends TaskProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DelayProcessor.class);

    public ScheduledFuture<?> process(DelayTask delayTask) {
        Runnable runnable = delayTask.getAction();
        return executorService.schedule(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
               logger.error("Error executing DelayTask: {}", e.getMessage(), e);
               throw new TaskProcessingException("DelayTask execution failed", e);
            }
        }, delayTask.getDelay(), delayTask.getTimeUnit());
    }

}

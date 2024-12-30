package com.vladproduction.processors;

import com.vladproduction.exceptions.TaskProcessingException;
import com.vladproduction.tasks.NowTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NowProcessor extends TaskProcessor {

    private static final Logger logger = LoggerFactory.getLogger(NowProcessor.class);

    public ScheduledFuture<?> process(NowTask nowTask){
        Runnable runnable = nowTask.getAction();
        return executorService.schedule(()->{
            try {
                runnable.run();
        }catch (Exception e){
                logger.error("Error executing NowTask: {}", e.getMessage(), e);
                throw new TaskProcessingException("NowTask execution failed", e);
        }
        }, 0, TimeUnit.SECONDS);
    }
}

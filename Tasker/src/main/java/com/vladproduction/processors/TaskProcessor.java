package com.vladproduction.processors;

import com.vladproduction.tasks.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskProcessor {
    protected static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    public void process(Task task){
        switch (task.getTaskType()){
            case NOW -> new NowProcessor().process((NowTask) task);
            case DELAY -> new DelayProcessor().process((DelayTask) task);
            case REPEATED -> new RepeatedProcessor().process((RepeatedTask) task);
            case SCHEDULE -> new ScheduleProcessor().process((ScheduleTask) task);
        }
    }
    public static void stopProcessing() throws InterruptedException {
        executorService.awaitTermination(15, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}

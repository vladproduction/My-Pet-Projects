package com.vladproduction.processors;

import com.vladproduction.tasks.*;

import java.util.Map;
import java.util.concurrent.*;

public class TaskProcessor {
    protected static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    private Map<Task, ScheduledFuture<?>> futures = new ConcurrentHashMap<>();
    public void process(Task task){
        ScheduledFuture<?> future;
        switch (task.getTaskType()){
            case NOW -> future = new NowProcessor().process((NowTask) task);
            case DELAY -> future = new DelayProcessor().process((DelayTask) task);
            case REPEATED -> future = new RepeatedProcessor().process((RepeatedTask) task);
            case SCHEDULE -> future = new ScheduleProcessor().process((ScheduleTask) task);
            default -> throw new IllegalArgumentException("Unsupported task type");
        }
        futures.put(task, future);
    }

    public void cancel(Task task){
        ScheduledFuture<?> future = futures.get(task);
        if(future != null){
            future.cancel(false);
        }
    }

    public static void stopProcessing() throws InterruptedException {
        executorService.shutdown(); // Prevent new tasks from being scheduled
        if (!executorService.awaitTermination(15, TimeUnit.SECONDS)) {
            executorService.shutdownNow(); // Force shutdown if not completed
        }
    }
}

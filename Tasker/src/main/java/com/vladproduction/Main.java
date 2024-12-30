package com.vladproduction;

import com.vladproduction.processors.TaskProcessor;
import com.vladproduction.tasks.*;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Create task examples
        Instant startNextTask = Instant.now().plus(10, TimeUnit.SECONDS.toChronoUnit());

        Task nowTask = new NowTask(() -> System.out.println("Now task executed"));
        Task delayTask = new DelayTask(() -> System.out.println("Delay task executed"), 20, TimeUnit.SECONDS);
        Task scheduleTask = new ScheduleTask(() -> System.out.println("Scheduled task executed"), startNextTask);
        Task repeatedTask = new RepeatedTask(() -> System.out.println("Repeated task executed"), Instant.now(), 5);

        TaskProcessor taskProcessor = new TaskProcessor();

        try {
            // Process tasks
            taskProcessor.process(nowTask);
            taskProcessor.process(delayTask);
            taskProcessor.process(scheduleTask);
            taskProcessor.process(repeatedTask);
        } catch (Exception e) {
            System.err.println("Error processing tasks: " + e.getMessage());
        }

        // Optionally, wait for a while before shutting down to see task outputs
        try {
            Thread.sleep(30000); // Wait enough time to see execution of scheduled and repeated tasks
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Clean up and shut down the processor
            try {
                TaskProcessor.stopProcessing();
                System.out.println("Task processing stopped successfully.");
            } catch (InterruptedException e) {
                System.err.println("Error shutting down task processing: " + e.getMessage());
            }
        }

    }
}

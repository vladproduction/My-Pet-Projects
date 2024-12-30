package com.vladproduction;

import com.vladproduction.processors.TaskProcessor;
import com.vladproduction.tasks.*;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Instant startNextTask = Instant.now().plus(10, TimeUnit.SECONDS.toChronoUnit());

        Task nowTask = new NowTask(() -> System.out.println("Now task"));
        Task delayTask = new DelayTask(() -> System.out.println("Delay task"), 20, TimeUnit.SECONDS);
        Task scheduleTask = new ScheduleTask(() -> System.out.println("Scheduled task"), startNextTask);
        Task repeatedTask = new RepeatedTask(() -> System.out.println("Repeated task"), Instant.now(), 5);

        TaskProcessor taskProcessor = new TaskProcessor();
        taskProcessor.process(nowTask);
        taskProcessor.process(delayTask);
        taskProcessor.process(scheduleTask);
        taskProcessor.process(repeatedTask);

        TaskProcessor.stopProcessing();

    }
}

package com.vladproduction.processors;

import com.vladproduction.tasks.DelayTask;

public class DelayProcessor extends TaskProcessor {
    /*public void process(DelayTask delayTask){
        Runnable runnable = delayTask.getAction();
        executorService.schedule(runnable, delayTask.getDelay(), delayTask.getTimeUnit());
    }*/

    public void process(DelayTask delayTask) {
        Runnable runnable = delayTask.getAction();
        executorService.schedule(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                // Log the error (use any logging framework or System.out for simplicity)
                System.err.println("Error executing DelayTask: " + e.getMessage());
            }
        }, delayTask.getDelay(), delayTask.getTimeUnit());
    }

}

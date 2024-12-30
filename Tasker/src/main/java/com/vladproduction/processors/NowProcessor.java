package com.vladproduction.processors;

import com.vladproduction.tasks.NowTask;

import java.util.concurrent.TimeUnit;

public class NowProcessor extends TaskProcessor {
    public void process(NowTask nowTask){
        Runnable runnable = nowTask.getAction();
        executorService.schedule(runnable, 0, TimeUnit.SECONDS);
    }
}

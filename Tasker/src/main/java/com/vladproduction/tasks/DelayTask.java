package com.vladproduction.tasks;

import java.util.concurrent.TimeUnit;

public class DelayTask extends Task{
    private long delay;
    private TimeUnit timeUnit;
    public DelayTask(Runnable action, long delay, TimeUnit timeUnit) {
        this.delay = delay;
        this.timeUnit = timeUnit;
        setAction(action);
        setTaskType(TaskType.DELAY);
    }
    public long getDelay() {
        return delay;
    }
    public void setDelay(long delay) {
        this.delay = delay;
    }
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}

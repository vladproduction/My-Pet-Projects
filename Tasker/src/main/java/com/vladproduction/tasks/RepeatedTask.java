package com.vladproduction.tasks;

import java.time.Instant;

public class RepeatedTask extends Task{
    private Instant startDate;
    private long nextRunInSeconds;
    public RepeatedTask(Runnable action, Instant startDate, long nextRunInSeconds) {
        this.startDate = startDate;
        this.nextRunInSeconds = nextRunInSeconds;
        setAction(action);
        setTaskType(TaskType.REPEATED);
    }
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    public long getNextRunInSeconds() {
        return nextRunInSeconds;
    }
    public void setNextRunInSeconds(long nextRunInSeconds) {
        this.nextRunInSeconds = nextRunInSeconds;
    }
}

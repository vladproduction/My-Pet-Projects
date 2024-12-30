package com.vladproduction.tasks;

import java.time.Instant;

public class ScheduleTask extends Task{
    private Instant startDate;
    public ScheduleTask(Runnable action, Instant startDate) {
        this.startDate = startDate;
        setAction(action);
        setTaskType(TaskType.SCHEDULE);
    }
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
}

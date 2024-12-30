package com.vladproduction.tasks;

public class NowTask extends Task{
    public NowTask(Runnable action) {
        setAction(action);
        setTaskType(TaskType.NOW);
    }
}

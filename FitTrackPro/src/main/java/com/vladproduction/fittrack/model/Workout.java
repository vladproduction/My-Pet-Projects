package com.vladproduction.fittrack.model;

public class Workout {

    private Long id;
    private String name;
    private int dayOfWeek;
    private Long workoutPlanId;

    public Workout() {}

    public Workout(Long id, String name, int dayOfWeek, Long workoutPlanId) {
        this.id = id;
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.workoutPlanId = workoutPlanId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getWorkoutPlanId() {
        return workoutPlanId;
    }

    public void setWorkoutPlanId(Long workoutPlanId) {
        this.workoutPlanId = workoutPlanId;
    }

    @Override
    public String toString() {
        return String.format("Workout[id=%d, name='%s', dayOfWeek=%d, workoutPlanId=%d]",
                id, name, dayOfWeek, workoutPlanId);
    }
}

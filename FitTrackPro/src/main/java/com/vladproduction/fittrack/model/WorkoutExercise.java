package com.vladproduction.fittrack.model;

public class WorkoutExercise {

    private Long id;
    private Long workoutId;
    private Long exerciseId;
    private int sets;
    private int reps;

    public WorkoutExercise() {}

    public WorkoutExercise(Long id, Long workoutId, Long exerciseId, int sets, int reps) {
        this.id = id;
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String toString() {
        return String.format("WorkoutExercise[id=%d, workoutId=%d, exerciseId=%d, sets=%d, reps=%d]",
                id, workoutId, exerciseId, sets, reps);
    }
}

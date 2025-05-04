package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.WorkoutExercise;

import java.util.List;

public interface WorkoutExerciseRepository {

    List<WorkoutExercise> findByWorkoutId(Long workoutId);
    void save(WorkoutExercise workoutExercise);
    void deleteByWorkoutId(Long workoutId);

}

package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.Workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository {

    Optional<Workout> findById(Long id);
    List<Workout> findAll();
    void save(Workout workout);
    void update(Workout workout);
    void delete(Long id);

}

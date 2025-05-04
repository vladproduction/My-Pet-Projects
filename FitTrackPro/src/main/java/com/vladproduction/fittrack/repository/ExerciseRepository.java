package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository {

    Optional<Exercise> findById(Long id);
    List<Exercise> findAll();
    void save(Exercise exercise);
    void update(Exercise exercise);
    void delete(Long id);

}

package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.WorkoutPlan;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository {

    Optional<WorkoutPlan> findById(Long id);
    List<WorkoutPlan> findAll();
    void save(WorkoutPlan plan);
    void update(WorkoutPlan plan);
    void delete(Long id);

}

package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.DietPlan;

import java.util.List;
import java.util.Optional;

public interface DietPlanRepository {

    Optional<DietPlan> findById(Long id);
    List<DietPlan> findAll();
    void save(DietPlan plan);
    void update(DietPlan plan);
    void delete(Long id);

}

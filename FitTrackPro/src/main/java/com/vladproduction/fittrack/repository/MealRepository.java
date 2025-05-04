package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.Meal;

import java.util.List;
import java.util.Optional;

public interface MealRepository {

    Optional<Meal> findById(Long id);
    List<Meal> findAll();
    void save(Meal meal);
    void update(Meal meal);
    void delete(Long id);

}

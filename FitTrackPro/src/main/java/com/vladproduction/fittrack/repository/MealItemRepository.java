package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.MealItem;

import java.util.List;

public interface MealItemRepository {

    List<MealItem> findByMealId(Long mealId);
    void save(MealItem mealItem);
    void deleteByMealId(Long mealId);

}

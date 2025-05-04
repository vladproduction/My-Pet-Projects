package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.FoodItem;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository {

    Optional<FoodItem> findById(Long id);
    List<FoodItem> findAll();
    void save(FoodItem foodItem);
    void update(FoodItem foodItem);
    void delete(Long id);

}

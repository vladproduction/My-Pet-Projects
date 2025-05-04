package com.vladproduction.fittrack.model;

public class MealItem {

    private Long id;
    private Long mealId;
    private Long foodItemId;
    private double quantity; // in grams or servings

    public MealItem() {}

    public MealItem(Long id, Long mealId, Long foodItemId, double quantity) {
        this.id = id;
        this.mealId = mealId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Long getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(Long foodItemId) {
        this.foodItemId = foodItemId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("MealItem[id=%d, mealId=%d, foodItemId=%d, quantity=%.1f]",
                id, mealId, foodItemId, quantity);
    }
}

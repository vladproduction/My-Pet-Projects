package com.vladproduction.fittrack.model;

public class Meal {

    private Long id;
    private String name;
    private String mealType; // e.g., "Breakfast", "Lunch", "Dinner"
    private Long dietPlanId;

    public Meal() {}

    public Meal(Long id, String name, String mealType, Long dietPlanId) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.dietPlanId = dietPlanId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Long getDietPlanId() {
        return dietPlanId;
    }

    public void setDietPlanId(Long dietPlanId) {
        this.dietPlanId = dietPlanId;
    }

    @Override
    public String toString() {
        return String.format("Meal[id=%d, name='%s', mealType='%s', dietPlanId=%d]",
                id, name, mealType, dietPlanId);
    }
}

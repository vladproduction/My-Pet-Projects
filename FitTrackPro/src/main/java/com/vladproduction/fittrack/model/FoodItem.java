package com.vladproduction.fittrack.model;

public class FoodItem {

    private Long id;
    private String name;
    private int calories;
    private double protein;
    private double carbs;
    private double fat;

    public FoodItem() {}

    public FoodItem(Long id, String name, int calories, double protein, double carbs, double fat) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    //Long id, String name, int calories, double protein, double carbs, double fat
    @Override
    public String toString() {
        return String.format("FoodItem[id=%d, name='%s', calories=%d, protein=%.1f, carbs=%.1f, fat=%.1f]",
                id, name, calories, protein, carbs, fat);
    }
}

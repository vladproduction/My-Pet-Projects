package com.vladproduction.fittrack.model;

public class Exercise {

    private Long id;
    private String name;
    private String description;
    private String muscleGroup;

    public Exercise() {}

    public Exercise(Long id, String name, String description, String muscleGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.muscleGroup = muscleGroup;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    @Override
    public String toString() {
        return String.format("Exercise[id=%d, name='%s', description='%s', muscleGroup='%s']",
                id, name, description, muscleGroup);
    }
}

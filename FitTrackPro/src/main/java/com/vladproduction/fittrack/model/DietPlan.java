package com.vladproduction.fittrack.model;

public class DietPlan {

    private Long id;
    private String name;
    private String goal; // e.g., "Weight Loss", "Muscle Gain"
    private Long coachId;

    public DietPlan() {}

    public DietPlan(Long id, String name, String goal, Long coachId) {
        this.id = id;
        this.name = name;
        this.goal = goal;
        this.coachId = coachId;
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

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    @Override
    public String toString() {
        return String.format("DietPlan[id=%d, name='%s', goal='%s', coachId=%d]",
                id, name, goal, coachId);
    }
}

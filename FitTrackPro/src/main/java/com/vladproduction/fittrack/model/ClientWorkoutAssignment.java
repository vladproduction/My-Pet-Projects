package com.vladproduction.fittrack.model;

import java.time.LocalDate;

public class ClientWorkoutAssignment {

    private Long id;
    private Long userId;
    private Long workoutPlanId;
    private LocalDate assignedAt;

    public ClientWorkoutAssignment() {}

    public ClientWorkoutAssignment(Long id, Long userId, Long workoutPlanId, LocalDate assignedAt) {
        this.id = id;
        this.userId = userId;
        this.workoutPlanId = workoutPlanId;
        this.assignedAt = assignedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWorkoutPlanId() {
        return workoutPlanId;
    }

    public void setWorkoutPlanId(Long workoutPlanId) {
        this.workoutPlanId = workoutPlanId;
    }

    public LocalDate getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDate assignedAt) {
        this.assignedAt = assignedAt;
    }

    @Override
    public String toString() {
        return String.format("ClientWorkoutAssignment[id=%d, userId=%d, workoutPlanId=%d, assignedAt=%s]",
                id, userId, workoutPlanId, assignedAt.toString());
    }
}

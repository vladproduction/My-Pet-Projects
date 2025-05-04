package com.vladproduction.fittrack.model;

import java.time.LocalDate;

public class ClientDietAssignment {

    private Long id;
    private Long userId;
    private Long dietPlanId;
    private LocalDate assignedAt;

    public ClientDietAssignment() {}

    public ClientDietAssignment(Long id, Long userId, Long dietPlanId, LocalDate assignedAt) {
        this.id = id;
        this.userId = userId;
        this.dietPlanId = dietPlanId;
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

    public Long getDietPlanId() {
        return dietPlanId;
    }

    public void setDietPlanId(Long dietPlanId) {
        this.dietPlanId = dietPlanId;
    }

    public LocalDate getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDate assignedAt) {
        this.assignedAt = assignedAt;
    }

    @Override
    public String toString() {
        return String.format("ClientDietAssignment[id=%d, userId=%d, dietPlanId=%d, assignedAt=%s]",
                id, userId, dietPlanId, assignedAt.toString());
    }
}

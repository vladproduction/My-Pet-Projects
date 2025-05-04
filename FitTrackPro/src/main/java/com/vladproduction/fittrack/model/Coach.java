package com.vladproduction.fittrack.model;

public class Coach {

    private Long id;
    private Long userId;
    private String specialty;

    public Coach() {}

    public Coach(Long id, Long userId, String specialty) {
        this.id = id;
        this.userId = userId;
        this.specialty = specialty;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return String.format("Coach[id=%d, userId=%d, specialty='%s']",
                id, userId, specialty);
    }
}

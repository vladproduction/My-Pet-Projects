package com.example.signinregisterapp;

public class Exercise {

    private String title;

    public Exercise() {
    }

    public Exercise(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

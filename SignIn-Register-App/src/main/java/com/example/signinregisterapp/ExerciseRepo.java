package com.example.signinregisterapp;

import java.util.List;

public interface ExerciseRepo {

    public void create(Exercise exercise, Person person);
    public List<Exercise> readAll(Person person);
    public void update(Exercise old, Exercise candidate,Person person);
    public void delete(Exercise title,Person person);
}

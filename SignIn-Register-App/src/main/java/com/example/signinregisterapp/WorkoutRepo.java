package com.example.signinregisterapp;

import java.util.List;

public interface WorkoutRepo {

    public void create(Workout workout, Person person);
    public List<Workout> readAll(Person person);
    public void update(Workout old, Workout candidate,Person person);
    public void delete(Workout name,Person person);

}

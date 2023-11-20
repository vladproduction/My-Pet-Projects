package com.example.signinregisterapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WorkoutExerciseManagerController {

    @FXML
    private Label currentWorkoutLabel;

    @FXML
    public void initialize(){

        String workoutName = Utils.currentWorkout.getName();
        currentWorkoutLabel.setText(workoutName);

    }

    public void backActionBTN(){
        System.out.println("back button action");
    }
}

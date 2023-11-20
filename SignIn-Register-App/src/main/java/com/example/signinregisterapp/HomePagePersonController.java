package com.example.signinregisterapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

import java.util.List;
import java.util.Optional;

public class HomePagePersonController {

    @FXML
    private TextField searchWorkoutsTF;
    @FXML
    private TextField searchExercisesTF;
    @FXML
    private TextArea showTextArea;
    @FXML
    private Label emailLB;
    @FXML
    private Label nameLB;
    @FXML
    private JFXListView<Workout> workoutLV;
    @FXML
    private JFXListView<Exercise> exercisesLV;
    private final WorkoutRepo workoutRepo = new WorkoutRepoImpl();
    private final ExerciseRepo exerciseRepo = new ExerciseRepoImpl();

    public void initialize(){
        //start position of view personal page
        List<Workout> workoutList = workoutRepo.readAll(Utils.currentPerson);
        workoutLV.getItems().addAll(workoutList);
        List<Exercise> exerciseList = exerciseRepo.readAll(Utils.currentPerson);
        exercisesLV.getItems().addAll(exerciseList);
        String personName = Utils.currentPerson.getLogin();
        String personEmail = Utils.currentPerson.getEmail();
        nameLB.setText(personName);
        emailLB.setText(personEmail);

        //switch window to workoutManager
        workoutLV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    int selectedIndex = workoutLV.getSelectionModel().getSelectedIndex();
                    List<Workout> workoutList = workoutLV.getItems();
                    Workout selectedWorkout = workoutList.get(selectedIndex);
                    Utils.currentWorkout = selectedWorkout;
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("workout-exercises-manager.fxml"));
                    Rectangle2D bounds = Screen.getPrimary().getBounds();
                    Scene scene = new Scene(fxmlLoader.load(), bounds.getWidth() * 0.50, bounds.getHeight() * 0.55);
                    Utils.stageHomePage.setScene(scene);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });

    }

    //search
    public void goEnterWorkoutAction(){
        String searchingItem = searchWorkoutsTF.getText().trim();
        List<Workout> workoutList = workoutLV.getItems();
        for (int i = 0; i < workoutList.size(); i++) {
            Workout item = workoutList.get(i);
            if(searchingItem.equals(item.getName())){
                showTextArea.clear();
                showTextArea.setText(item.getName());
            }
        }
    }
    public void goEnterExerciseAction(){
        String searchingItem = searchExercisesTF.getText().trim();
        List<Exercise> exerciseList = exercisesLV.getItems();
        for (int i = 0; i < exerciseList.size(); i++) {
            Exercise item = exerciseList.get(i);
            if(searchingItem.equals(item.getTitle())){
                showTextArea.clear();
                showTextArea.setText(item.getTitle());
            }
        }
    }


    //workout BTN:
    public void addWorkoutAction(){

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Add Workout");
        Optional<String> result = inputDialog.showAndWait();
        String workoutName = result.get();
        Workout workout = new Workout();
        workout.setName(workoutName);
        workoutRepo.create(workout, Utils.currentPerson);
        workoutLV.getItems().clear();
        List<Workout> workoutList = workoutRepo.readAll(Utils.currentPerson);
        workoutLV.getItems().addAll(workoutList);

    }
    public void updateWorkoutAction(){
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("New workout name");
        Optional<String> result = inputDialog.showAndWait();
        String workoutName = result.get();
        Workout candidate = new Workout();
        candidate.setName(workoutName);

        int selectedIndex = workoutLV.getSelectionModel().getSelectedIndex();
        List<Workout> workoutList = workoutLV.getItems();
        Workout old = workoutList.get(selectedIndex);
        workoutRepo.update(old,candidate,Utils.currentPerson);

        workoutLV.getItems().clear();
        List<Workout> workoutListView = workoutRepo.readAll(Utils.currentPerson);
        workoutLV.getItems().addAll(workoutListView);

    }
    public void deleteWorkoutAction(){

        int selectedIndex = workoutLV.getSelectionModel().getSelectedIndex();
        List<Workout> workoutList = workoutLV.getItems();
        Workout selectedToDelete = workoutList.get(selectedIndex);
        workoutRepo.delete(selectedToDelete,Utils.currentPerson);

        workoutLV.getItems().clear();
        List<Workout> workoutListView = workoutRepo.readAll(Utils.currentPerson);
        workoutLV.getItems().addAll(workoutListView);

    }


    //exercise BTN:
    public void addExercisesAction(){

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Add Exercise");
        Optional<String> result = inputDialog.showAndWait();
        String exerciseName = result.get();
        Exercise exercise = new Exercise();
        exercise.setTitle(exerciseName);
        exerciseRepo.create(exercise, Utils.currentPerson);
        exercisesLV.getItems().clear();
        List<Exercise> exerciseList = exerciseRepo.readAll(Utils.currentPerson);
        exercisesLV.getItems().addAll(exerciseList);
    }
    public void updateExercisesAction(){

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Update exercise title");
        Optional<String> result = inputDialog.showAndWait();
        String exerciseName = result.get();
        Exercise candidate = new Exercise();
        candidate.setTitle(exerciseName);

        int selectedIndex = exercisesLV.getSelectionModel().getSelectedIndex();
        List<Exercise> exerciseList = exercisesLV.getItems();
        Exercise old = exerciseList.get(selectedIndex);
        exerciseRepo.update(old,candidate,Utils.currentPerson);

        exercisesLV.getItems().clear();
        List<Exercise> exerciseListView = exerciseRepo.readAll(Utils.currentPerson);
        exercisesLV.getItems().addAll(exerciseListView);

    }
    public void deleteExercisesAction(){

        int selectedIndex = exercisesLV.getSelectionModel().getSelectedIndex();
        List<Exercise> exerciseList = exercisesLV.getItems();
        Exercise selectedToDelete = exerciseList.get(selectedIndex);
        exerciseRepo.delete(selectedToDelete,Utils.currentPerson);
        exercisesLV.getItems().clear();
        List<Exercise> exerciseListView = exerciseRepo.readAll(Utils.currentPerson);
        exercisesLV.getItems().addAll(exerciseListView);

    }


    //showSelectedBTN
    public void showSelectedWorkoutAction() {
        int selectedIndexWorkout = workoutLV.getSelectionModel().getSelectedIndex();
        List<Workout> workouts = workoutLV.getItems();
        Workout currentWorkout = workouts.get(selectedIndexWorkout);
        String currentWorkoutText = currentWorkout.toString();
        showTextArea.setText(currentWorkoutText);
    }
    public void showSelectedExerciseAction() {
        int selectedIndexExercise = exercisesLV.getSelectionModel().getSelectedIndex();
        List<Exercise> exercises = exercisesLV.getItems();
        Exercise currentExercise = exercises.get(selectedIndexExercise);
        String currentExerciseText = currentExercise.toString();
        showTextArea.setText(currentExerciseText);
    }
}

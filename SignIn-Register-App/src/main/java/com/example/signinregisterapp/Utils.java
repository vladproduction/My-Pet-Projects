package com.example.signinregisterapp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Utils {

    public static Person currentPerson;
    public static Stage stageHomePage;
    public static Workout currentWorkout;

    /*choose your prefer alert message*/

    ////Utils.showError(e); //to the file
    public static void showError(Exception e){
        File file = new File("error.txt");
        try{
            PrintStream ps = new PrintStream(file);
            e.printStackTrace(ps);
        }catch (FileNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }

    ////Utils.showErrorWithAlert(e); //big message in Alert window
    public static void showErrorWithAlert(Exception ex) {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PrintStream ps = new PrintStream(out);
            ex.printStackTrace(ps);
            byte [] content = out.toByteArray();
            String errorMessage = new String(content);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(errorMessage);
            alert.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void showErrorShortAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ex.getMessage());
        alert.show();
    }
}

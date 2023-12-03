package com.example.moviecatalogapp;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.solution2.administration.model.Admin;

public class UtilsAttention {

    public static Admin currentAdmin;
    public static Stage stage;

    public static void showErrorShortAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(ex.getMessage());
        alert.show();

    }


}

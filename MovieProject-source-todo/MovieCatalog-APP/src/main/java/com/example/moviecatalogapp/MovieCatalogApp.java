package com.example.moviecatalogapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieCatalogApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieCatalogApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("MovieCatalog-App");
        stage.setScene(scene);
        UtilsAttention.stage = stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
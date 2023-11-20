package com.example.multiview;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane pane = new BorderPane();

        HBox taskbar = new HBox();
        taskbar.setPadding(new Insets(10, 10, 10, 10));
        taskbar.setAlignment(Pos.CENTER);
        taskbar.setPrefHeight(150);

        StackPane stackPane = new StackPane();

        Image image1 = new Image(getClass().getResource("/1.jpg").toString());
        Image image2 = new Image(getClass().getResource("/2.png").toString());
        Image image3 = new Image(getClass().getResource("/3.png").toString());
        Image image4 = new Image(getClass().getResource("/4.jpg").toString());
        Image image5 = new Image(getClass().getResource("/5.jpg").toString());

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);
        ImageView imageView5 = new ImageView(image5);

        taskbar.getChildren().addAll(imageView1,imageView2,imageView3,imageView4,imageView5);

        imageView1.setOnMouseEntered(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                System.out.println("mouse entered");
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView1);
                animationGrow.setToX(1.3);
                animationGrow.setToY(1.3);
                animationGrow.play();
            }
        });
        imageView1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse exit");
                final ScaleTransition animationShrink = new ScaleTransition(Duration.valueOf("300ms"), imageView1);
                animationShrink.setToX(1);
                animationShrink.setToY(1);
                animationShrink.play();
            }
        });

        imageView2.setOnMouseEntered(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                System.out.println("mouse entered");
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView2);
                animationGrow.setToX(1.3);
                animationGrow.setToY(1.3);
                animationGrow.play();

            }
        });
        imageView2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse exit");
                final ScaleTransition animationShrink = new ScaleTransition(Duration.valueOf("300ms"), imageView2);
                animationShrink.setToX(1);
                animationShrink.setToY(1);
                animationShrink.play();
            }
        });

        imageView3.setOnMouseEntered(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                System.out.println("mouse entered");
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView3);
                animationGrow.setToX(1.3);
                animationGrow.setToY(1.3);
                animationGrow.play();
            }
        });
        imageView3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse exit");
                final ScaleTransition animationShrink = new ScaleTransition(Duration.valueOf("300ms"), imageView3);
                animationShrink.setToX(1);
                animationShrink.setToY(1);
                animationShrink.play();
            }
        });

        imageView4.setOnMouseEntered(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                System.out.println("mouse entered");
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView4);
                animationGrow.setToX(1.3);
                animationGrow.setToY(1.3);
                animationGrow.play();
            }
        });
        imageView4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse exit");
                final ScaleTransition animationShrink = new ScaleTransition(Duration.valueOf("300ms"), imageView4);
                animationShrink.setToX(1);
                animationShrink.setToY(1);
                animationShrink.play();
            }
        });

        imageView5.setOnMouseEntered(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                System.out.println("mouse entered");
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView5);
                animationGrow.setToX(1.3);
                animationGrow.setToY(1.3);
                animationGrow.play();
            }
        });
        imageView5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse exit");
                final ScaleTransition animationShrink = new ScaleTransition(Duration.valueOf("300ms"), imageView5);
                animationShrink.setToX(1);
                animationShrink.setToY(1);
                animationShrink.play();
            }
        });


        //video-1
        imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Media media = new Media(getClass().getResource("/1.mp4").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                MediaView mediaView = new MediaView(mediaPlayer);
                mediaView.setFitHeight(500);
                mediaView.setFitWidth(600);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(mediaView);
                mediaPlayer.play();
            }
        });

        //video-2
        imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Media media2 = new Media(getClass().getResource("/2.mp4").toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
                MediaView mediaView2 = new MediaView(mediaPlayer2);
                mediaView2.setFitHeight(500);
                mediaView2.setFitWidth(600);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(mediaView2);
                mediaPlayer2.play();
            }
        });

        //video-3
        imageView3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Media media3 = new Media(getClass().getResource("/3.mp4").toString());
                MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
                MediaView mediaView3 = new MediaView(mediaPlayer3);
                mediaView3.setFitHeight(500);
                mediaView3.setFitWidth(600);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(mediaView3);
                mediaPlayer3.play();
            }
        });

        //video-4
        imageView4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Media media4 = new Media(getClass().getResource("/4.mp4").toString());
                MediaPlayer mediaPlayer4 = new MediaPlayer(media4);
                MediaView mediaView4 = new MediaView(mediaPlayer4);
                mediaView4.setFitHeight(500);
                mediaView4.setFitWidth(600);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(mediaView4);
                mediaPlayer4.play();
            }
        });

        //video-5
        imageView5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Media media5 = new Media(getClass().getResource("/5.mp4").toString());
                MediaPlayer mediaPlayer5 = new MediaPlayer(media5);
                MediaView mediaView5 = new MediaView(mediaPlayer5);
                mediaView5.setFitHeight(500);
                mediaView5.setFitWidth(600);
                stackPane.getChildren().clear();
                stackPane.getChildren().add(mediaView5);
                mediaPlayer5.play();
            }
        });




        pane.setCenter(stackPane);
        pane.setBottom(taskbar);
        Scene scene = new Scene(pane, 600, 600, Color.LIGHTGREEN);
        stage.setTitle("Multi-View");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
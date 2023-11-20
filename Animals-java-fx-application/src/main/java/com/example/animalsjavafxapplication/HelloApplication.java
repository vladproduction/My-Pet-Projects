package com.example.animalsjavafxapplication;

import javafx.animation.ScaleTransition;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Reflection;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane pane = new BorderPane();
        HBox taskbar = new HBox(10);
        taskbar.setPadding(new Insets(10, 30, 50, 30));
        taskbar.setAlignment(Pos.CENTER);
        taskbar.setPrefHeight(230);

        StackPane stackPane = new StackPane();
        pane.setCenter(stackPane);


        URL url = getClass().getResource("/1-lion.png");
        //System.out.println("url="+url);
        Image image1 = new Image(url.toString());
        Image image2 = new Image(getClass().getResource("/2-elephant.png").toString());
        Image image3 = new Image(getClass().getResource("/3-capybara.png").toString());
        Image image4 = new Image(getClass().getResource("/4-eagle.png").toString());
        Image image5 = new Image(getClass().getResource("/5-ocean-fish.png").toString());

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);
        ImageView imageView5 = new ImageView(image5);

        //creating effects for our images:
        imageView1.setEffect(new Reflection());
        imageView2.setEffect(new Reflection());
        imageView3.setEffect(new Reflection());
        imageView4.setEffect(new Reflection());
        imageView5.setEffect(new Reflection());


        //event for mouse moving:
        imageView1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse enter");
                //(in action picture arisen):
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView1);
                animationGrow.setToX(1.9);
                animationGrow.setToY(1.9);
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
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse enter");
                //(in action picture arisen):
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView2);
                animationGrow.setToX(1.9);
                animationGrow.setToY(1.9);
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
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse enter");
                //(in action picture arisen):
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView3);
                animationGrow.setToX(1.9);
                animationGrow.setToY(1.9);
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
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse enter");
                //(in action picture arisen):
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView4);
                animationGrow.setToX(1.9);
                animationGrow.setToY(1.9);
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
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse enter");
                //(in action picture arisen):
                ScaleTransition animationGrow = new ScaleTransition(Duration.valueOf("300ms"), imageView5);
                animationGrow.setToX(1.9);
                animationGrow.setToY(1.9);
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


        //event for mouse clicking:

        URL urlVideo = getClass().getResource("/Rotate_LoopVideo_preview.mp4");
        Media media = new Media(urlVideo.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(400);
        mediaView.setFitWidth(400);
        imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stackPane.getChildren().clear();
                stackPane.getChildren().add(mediaView);
                mediaPlayer.play();
            }
        });
        imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("Chart Title");
                xAxis.setLabel("X-Axis");
                yAxis.setLabel("Y-Axis");
                mediaPlayer.stop();
                stackPane.getChildren().clear();

                //get random data:

                //chart-1
                XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
                series1.setName("Random Data <series1>");
                Random random = new Random();
                int sizeRandom = random.nextInt(15);
                for (int i = 0; i < sizeRandom; i++) {
                    double x = i;
                    double y = random.nextDouble();
                    series1.getData().add(new XYChart.Data<>(x, y));
                }
                //chart-2
                XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
                series2.setName("Random Data <series2>");
                for (int i = 0; i < sizeRandom; i++) {
                    double x = i;
                    double y = random.nextDouble();
                    series2.getData().add(new XYChart.Data<>(x, y));
                }
                //chart-3
                XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
                series3.setName("Random Data <series3>");
                for (int i = 0; i < sizeRandom; i++) {
                    double x = i;
                    double y = random.nextDouble();
                    series3.getData().add(new XYChart.Data<>(x, y));
                }
                chart.getData().add(series1);
                chart.getData().add(series2);
                chart.getData().add(series3);
                stackPane.getChildren().add(chart);
            }
        });


        Accordion accordion = new Accordion();
        Image im1 = new Image(getClass().getResource("/1-lion.png").toString());
        Image im2 = new Image(getClass().getResource("/2-elephant.png").toString());
        Image im3 = new Image(getClass().getResource("/3-capybara.png").toString());
        Image im4 = new Image(getClass().getResource("/4-eagle.png").toString());
        Image im5 = new Image(getClass().getResource("/5-ocean-fish.png").toString());
        ImageView imageV1 = new ImageView(im1);
        ImageView imageV2 = new ImageView(im2);
        ImageView imageV3 = new ImageView(im3);
        ImageView imageV4 = new ImageView(im4);
        ImageView imageV5 = new ImageView(im5);
        TitledPane t1 = new TitledPane("1-lion", imageV1);
        TitledPane t2 = new TitledPane("2-elephant", imageV2);
        TitledPane t3 = new TitledPane("3-capybara", imageV3);
        TitledPane t4 = new TitledPane("4-eagle", imageV4);
        TitledPane t5 = new TitledPane("5-ocean-fish", imageV5);
        accordion.getPanes().addAll(t1, t2, t3, t4, t5);
        imageView3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                stackPane.getChildren().clear();

                stackPane.getChildren().add(accordion);
            }
        });

        WebView webView = new WebView();
        imageView4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                stackPane.getChildren().clear();
                WebEngine webEngine = webView.getEngine();
                webEngine.load("http://google.com");
                stackPane.getChildren().add(webView);

            }
        });

        imageView5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.stop();
                stackPane.getChildren().clear();
                ListView listView = new ListView();
                listView.getItems().add("-fx-background-color: green;");
                listView.getItems().add("-fx-background-color: linear (0%,0%) to (100%,100%) stops (0.0,aqua) (1.0,red);");
                listView.getItems().add("-fx-background-color: transparent;");
                listView.getItems().add("-fx-opacity: 0.3;");
                listView.getItems().add("-fx-opacity: 1;");
                // заполняем его стилями
                // через binding связываем стиль панели задач с выбранным элементом списка
                taskbar.styleProperty().bind(listView.getSelectionModel().selectedItemProperty());

                stackPane.getChildren().add(listView);
            }
        });


        taskbar.getChildren().addAll(imageView1, imageView2, imageView3, imageView4, imageView5);
        pane.setBottom(taskbar);
        Scene scene = new Scene(pane, 920, 650, Color.LIGHTGRAY);
        stage.setTitle("Zoo-Animals");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
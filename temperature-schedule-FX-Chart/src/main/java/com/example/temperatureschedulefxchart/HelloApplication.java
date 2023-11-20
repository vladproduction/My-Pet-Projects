package com.example.temperatureschedulefxchart;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane pane = new BorderPane();
        HBox taskbar = new HBox();
        taskbar.setPadding(new Insets(10, 30, 50, 30));
        taskbar.setAlignment(Pos.BOTTOM_CENTER);
        taskbar.setPrefHeight(50);

        StackPane stackPane = new StackPane();
        pane.setCenter(stackPane);

        Image image1 = new Image(getClass().getResource("/1.png").toString());
        Image image2 = new Image(getClass().getResource("/2.png").toString());
        Image image3 = new Image(getClass().getResource("/3.png").toString());
        Image image4 = new Image(getClass().getResource("/4.png").toString());
        Image image5 = new Image(getClass().getResource("/5.png").toString());
        Image image6 = new Image(getClass().getResource("/6.png").toString());
        Image image7 = new Image(getClass().getResource("/7.png").toString());
        Image image8 = new Image(getClass().getResource("/8.png").toString());
        Image image9 = new Image(getClass().getResource("/9.png").toString());
        Image image10= new Image(getClass().getResource("/10.png").toString());
        Image image11 = new Image(getClass().getResource("/11.png").toString());
        Image image12 = new Image(getClass().getResource("/12.png").toString());

        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);
        ImageView imageView5 = new ImageView(image5);
        ImageView imageView6 = new ImageView(image6);
        ImageView imageView7 = new ImageView(image7);
        ImageView imageView8 = new ImageView(image8);
        ImageView imageView9 = new ImageView(image9);
        ImageView imageView10 = new ImageView(image10);
        ImageView imageView11 = new ImageView(image11);
        ImageView imageView12 = new ImageView(image12);

        imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("January");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesJanuary = new XYChart.Series();
                seriesJanuary.setName("January");
                seriesJanuary.getData().add(new XYChart.Data(1,4));
                seriesJanuary.getData().add(new XYChart.Data(3,2));
                seriesJanuary.getData().add(new XYChart.Data(5,0));
                seriesJanuary.getData().add(new XYChart.Data(7,-1));
                seriesJanuary.getData().add(new XYChart.Data(9,0));
                seriesJanuary.getData().add(new XYChart.Data(11,-3));
                seriesJanuary.getData().add(new XYChart.Data(13,-4));
                seriesJanuary.getData().add(new XYChart.Data(15,-2));
                seriesJanuary.getData().add(new XYChart.Data(17,-3));
                seriesJanuary.getData().add(new XYChart.Data(19,-1));
                seriesJanuary.getData().add(new XYChart.Data(21,-1));
                seriesJanuary.getData().add(new XYChart.Data(23,0));
                seriesJanuary.getData().add(new XYChart.Data(25,1));
                seriesJanuary.getData().add(new XYChart.Data(27,2));
                seriesJanuary.getData().add(new XYChart.Data(29,0));
                seriesJanuary.getData().add(new XYChart.Data(31,-3));

                chart.getData().add(seriesJanuary);
                stackPane.getChildren().add(chart);
            }
        });

        imageView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("February");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesFeb = new XYChart.Series();
                seriesFeb.setName("February");
                seriesFeb.getData().add(new XYChart.Data(1,-2));
                seriesFeb.getData().add(new XYChart.Data(3,-3));
                seriesFeb.getData().add(new XYChart.Data(5,-10));
                seriesFeb.getData().add(new XYChart.Data(7,-5));
                seriesFeb.getData().add(new XYChart.Data(9,0));
                seriesFeb.getData().add(new XYChart.Data(11,1));
                seriesFeb.getData().add(new XYChart.Data(13,3));
                seriesFeb.getData().add(new XYChart.Data(15,10));
                seriesFeb.getData().add(new XYChart.Data(17,11));
                seriesFeb.getData().add(new XYChart.Data(19,4));
                seriesFeb.getData().add(new XYChart.Data(21,0));
                seriesFeb.getData().add(new XYChart.Data(23,-4));
                seriesFeb.getData().add(new XYChart.Data(25,-2));
                seriesFeb.getData().add(new XYChart.Data(27,1));
                seriesFeb.getData().add(new XYChart.Data(28,0));


                chart.getData().add(seriesFeb);
                stackPane.getChildren().add(chart);
            }
        });

        imageView3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("March");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesMarch = new XYChart.Series();
                seriesMarch.setName("March");
                seriesMarch.getData().add(new XYChart.Data(1,1));
                seriesMarch.getData().add(new XYChart.Data(3,2));
                seriesMarch.getData().add(new XYChart.Data(5,4));
                seriesMarch.getData().add(new XYChart.Data(7,7));
                seriesMarch.getData().add(new XYChart.Data(9,1));
                seriesMarch.getData().add(new XYChart.Data(11,3));
                seriesMarch.getData().add(new XYChart.Data(13,8));
                seriesMarch.getData().add(new XYChart.Data(15,12));
                seriesMarch.getData().add(new XYChart.Data(17,14));
                seriesMarch.getData().add(new XYChart.Data(19,13));
                seriesMarch.getData().add(new XYChart.Data(21,10));
                seriesMarch.getData().add(new XYChart.Data(23,15));
                seriesMarch.getData().add(new XYChart.Data(25,12));
                seriesMarch.getData().add(new XYChart.Data(27,16));
                seriesMarch.getData().add(new XYChart.Data(29,7));
                seriesMarch.getData().add(new XYChart.Data(30,10));

                chart.getData().add(seriesMarch);
                stackPane.getChildren().add(chart);
            }
        });

        imageView4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("April");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesApril = new XYChart.Series();
                seriesApril.setName("April");
                seriesApril.getData().add(new XYChart.Data(1,12));
                seriesApril.getData().add(new XYChart.Data(3,13));
                seriesApril.getData().add(new XYChart.Data(5,11));
                seriesApril.getData().add(new XYChart.Data(7,10));
                seriesApril.getData().add(new XYChart.Data(9,14));
                seriesApril.getData().add(new XYChart.Data(11,17));
                seriesApril.getData().add(new XYChart.Data(13,19));
                seriesApril.getData().add(new XYChart.Data(15,20));
                seriesApril.getData().add(new XYChart.Data(17,22));
                seriesApril.getData().add(new XYChart.Data(19,20));
                seriesApril.getData().add(new XYChart.Data(21,24));
                seriesApril.getData().add(new XYChart.Data(23,18));
                seriesApril.getData().add(new XYChart.Data(25,19));
                seriesApril.getData().add(new XYChart.Data(27,20));
                seriesApril.getData().add(new XYChart.Data(29,19));
                seriesApril.getData().add(new XYChart.Data(31,20));

                chart.getData().add(seriesApril);
                stackPane.getChildren().add(chart);
            }
        });

        imageView5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("May");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesMay = new XYChart.Series();
                seriesMay.setName("May");
                seriesMay.getData().add(new XYChart.Data(1,19));
                seriesMay.getData().add(new XYChart.Data(3,20));
                seriesMay.getData().add(new XYChart.Data(5,24));
                seriesMay.getData().add(new XYChart.Data(7,20));
                seriesMay.getData().add(new XYChart.Data(9,22));
                seriesMay.getData().add(new XYChart.Data(11,24));
                seriesMay.getData().add(new XYChart.Data(13,20));
                seriesMay.getData().add(new XYChart.Data(15,19));
                seriesMay.getData().add(new XYChart.Data(17,22));
                seriesMay.getData().add(new XYChart.Data(19,20));
                seriesMay.getData().add(new XYChart.Data(21,24));
                seriesMay.getData().add(new XYChart.Data(23,20));
                seriesMay.getData().add(new XYChart.Data(25,28));
                seriesMay.getData().add(new XYChart.Data(27,22));
                seriesMay.getData().add(new XYChart.Data(29,23));
                seriesMay.getData().add(new XYChart.Data(31,24));

                chart.getData().add(seriesMay);
                stackPane.getChildren().add(chart);
            }
        });

        imageView6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("June");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesJune = new XYChart.Series();
                seriesJune.setName("June");
                seriesJune.getData().add(new XYChart.Data(1,24));
                seriesJune.getData().add(new XYChart.Data(3,20));
                seriesJune.getData().add(new XYChart.Data(5,24));
                seriesJune.getData().add(new XYChart.Data(7,27));
                seriesJune.getData().add(new XYChart.Data(9,25));
                seriesJune.getData().add(new XYChart.Data(11,24));
                seriesJune.getData().add(new XYChart.Data(13,22));
                seriesJune.getData().add(new XYChart.Data(15,24));
                seriesJune.getData().add(new XYChart.Data(17,22));
                seriesJune.getData().add(new XYChart.Data(19,27));
                seriesJune.getData().add(new XYChart.Data(21,24));
                seriesJune.getData().add(new XYChart.Data(23,26));
                seriesJune.getData().add(new XYChart.Data(25,28));
                seriesJune.getData().add(new XYChart.Data(27,26));
                seriesJune.getData().add(new XYChart.Data(29,30));
                seriesJune.getData().add(new XYChart.Data(30,29));

                chart.getData().add(seriesJune);
                stackPane.getChildren().add(chart);
            }
        });

        imageView7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("July");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesJuly = new XYChart.Series();
                seriesJuly.setName("July");
                seriesJuly.getData().add(new XYChart.Data(1,21));
                seriesJuly.getData().add(new XYChart.Data(3,22));
                seriesJuly.getData().add(new XYChart.Data(5,24));
                seriesJuly.getData().add(new XYChart.Data(7,27));
                seriesJuly.getData().add(new XYChart.Data(9,25));
                seriesJuly.getData().add(new XYChart.Data(11,28));
                seriesJuly.getData().add(new XYChart.Data(13,28));
                seriesJuly.getData().add(new XYChart.Data(15,29));
                seriesJuly.getData().add(new XYChart.Data(17,32));
                seriesJuly.getData().add(new XYChart.Data(19,33));
                seriesJuly.getData().add(new XYChart.Data(21,32));
                seriesJuly.getData().add(new XYChart.Data(23,30));
                seriesJuly.getData().add(new XYChart.Data(25,34));
                seriesJuly.getData().add(new XYChart.Data(27,32));
                seriesJuly.getData().add(new XYChart.Data(29,28));
                seriesJuly.getData().add(new XYChart.Data(31,29));

                chart.getData().add(seriesJuly);
                stackPane.getChildren().add(chart);
            }
        });

        imageView8.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("August");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesAugust = new XYChart.Series();
                seriesAugust.setName("August");
                seriesAugust.getData().add(new XYChart.Data(1,31));
                seriesAugust.getData().add(new XYChart.Data(3,30));
                seriesAugust.getData().add(new XYChart.Data(5,29));
                seriesAugust.getData().add(new XYChart.Data(7,29));
                seriesAugust.getData().add(new XYChart.Data(9,31));
                seriesAugust.getData().add(new XYChart.Data(11,28));
                seriesAugust.getData().add(new XYChart.Data(13,28));
                seriesAugust.getData().add(new XYChart.Data(15,27));
                seriesAugust.getData().add(new XYChart.Data(17,24));
                seriesAugust.getData().add(new XYChart.Data(19,25));
                seriesAugust.getData().add(new XYChart.Data(21,24));
                seriesAugust.getData().add(new XYChart.Data(23,26));
                seriesAugust.getData().add(new XYChart.Data(25,27));
                seriesAugust.getData().add(new XYChart.Data(27,26));
                seriesAugust.getData().add(new XYChart.Data(29,24));
                seriesAugust.getData().add(new XYChart.Data(31,23));

                chart.getData().add(seriesAugust);
                stackPane.getChildren().add(chart);
            }
        });

        imageView9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("September");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesSeptember = new XYChart.Series();
                seriesSeptember.setName("September");
                seriesSeptember.getData().add(new XYChart.Data(1,19));
                seriesSeptember.getData().add(new XYChart.Data(3,20));
                seriesSeptember.getData().add(new XYChart.Data(5,24));
                seriesSeptember.getData().add(new XYChart.Data(7,27));
                seriesSeptember.getData().add(new XYChart.Data(9,22));
                seriesSeptember.getData().add(new XYChart.Data(11,21));
                seriesSeptember.getData().add(new XYChart.Data(13,22));
                seriesSeptember.getData().add(new XYChart.Data(15,19));
                seriesSeptember.getData().add(new XYChart.Data(17,22));
                seriesSeptember.getData().add(new XYChart.Data(19,20));
                seriesSeptember.getData().add(new XYChart.Data(21,19));
                seriesSeptember.getData().add(new XYChart.Data(23,20));
                seriesSeptember.getData().add(new XYChart.Data(25,22));
                seriesSeptember.getData().add(new XYChart.Data(27,21));
                seriesSeptember.getData().add(new XYChart.Data(29,20));
                seriesSeptember.getData().add(new XYChart.Data(30,19));

                chart.getData().add(seriesSeptember);
                stackPane.getChildren().add(chart);
            }
        });

        imageView10.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("October");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesOctober = new XYChart.Series();
                seriesOctober.setName("October");
                seriesOctober.getData().add(new XYChart.Data(1,19));
                seriesOctober.getData().add(new XYChart.Data(3,20));
                seriesOctober.getData().add(new XYChart.Data(5,17));
                seriesOctober.getData().add(new XYChart.Data(7,17));
                seriesOctober.getData().add(new XYChart.Data(9,18));
                seriesOctober.getData().add(new XYChart.Data(11,20));
                seriesOctober.getData().add(new XYChart.Data(13,22));
                seriesOctober.getData().add(new XYChart.Data(15,24));
                seriesOctober.getData().add(new XYChart.Data(17,22));
                seriesOctober.getData().add(new XYChart.Data(19,19));
                seriesOctober.getData().add(new XYChart.Data(21,20));
                seriesOctober.getData().add(new XYChart.Data(23,18));
                seriesOctober.getData().add(new XYChart.Data(25,17));
                seriesOctober.getData().add(new XYChart.Data(27,17));
                seriesOctober.getData().add(new XYChart.Data(29,16));
                seriesOctober.getData().add(new XYChart.Data(31,14));

                chart.getData().add(seriesOctober);
                stackPane.getChildren().add(chart);
            }
        });

        imageView11.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("November");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesNovember = new XYChart.Series();
                seriesNovember.setName("November");
                seriesNovember.getData().add(new XYChart.Data(1,12));
                seriesNovember.getData().add(new XYChart.Data(3,13));
                seriesNovember.getData().add(new XYChart.Data(5,14));
                seriesNovember.getData().add(new XYChart.Data(7,12));
                seriesNovember.getData().add(new XYChart.Data(9,12));
                seriesNovember.getData().add(new XYChart.Data(11,11));
                seriesNovember.getData().add(new XYChart.Data(13,10));
                seriesNovember.getData().add(new XYChart.Data(15,9));
                seriesNovember.getData().add(new XYChart.Data(17,7));
                seriesNovember.getData().add(new XYChart.Data(19,2));
                seriesNovember.getData().add(new XYChart.Data(21,0));
                seriesNovember.getData().add(new XYChart.Data(23,-1));
                seriesNovember.getData().add(new XYChart.Data(25,-2));
                seriesNovember.getData().add(new XYChart.Data(27,0));
                seriesNovember.getData().add(new XYChart.Data(29,5));
                seriesNovember.getData().add(new XYChart.Data(31,4));

                chart.getData().add(seriesNovember);
                stackPane.getChildren().add(chart);
            }
        });

        imageView12.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                //create chart:
                LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
                chart.setTitle("December");
                xAxis.setLabel("Day number");
                yAxis.setLabel("Temperature (tC)");
                stackPane.getChildren().clear();

                XYChart.Series<Number, Number> seriesDecember = new XYChart.Series();
                seriesDecember.setName("December");
                seriesDecember.getData().add(new XYChart.Data(1,4));
                seriesDecember.getData().add(new XYChart.Data(3,2));
                seriesDecember.getData().add(new XYChart.Data(5,0));
                seriesDecember.getData().add(new XYChart.Data(7,0));
                seriesDecember.getData().add(new XYChart.Data(9,5));
                seriesDecember.getData().add(new XYChart.Data(11,7));
                seriesDecember.getData().add(new XYChart.Data(13,2));
                seriesDecember.getData().add(new XYChart.Data(15,0));
                seriesDecember.getData().add(new XYChart.Data(17,-1));
                seriesDecember.getData().add(new XYChart.Data(19,-3));
                seriesDecember.getData().add(new XYChart.Data(21,-5));
                seriesDecember.getData().add(new XYChart.Data(23,-9));
                seriesDecember.getData().add(new XYChart.Data(25,-12));
                seriesDecember.getData().add(new XYChart.Data(27,-8));
                seriesDecember.getData().add(new XYChart.Data(29,-10));
                seriesDecember.getData().add(new XYChart.Data(31,-4));

                chart.getData().add(seriesDecember);
                stackPane.getChildren().add(chart);
            }
        });

        taskbar.getChildren().addAll(imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,
                                     imageView7,imageView8,imageView9,imageView10,imageView11,imageView12);
        pane.setBottom(taskbar);
        Scene scene = new Scene(pane, 850, 550);
        stage.setTitle("Temperature Schedule -Area Chart FX-");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
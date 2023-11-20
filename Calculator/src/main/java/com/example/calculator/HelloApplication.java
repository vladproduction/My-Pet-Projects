package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Button one = new Button("1");
    public static Button two = new Button("2");
    public static Button tree = new Button("3");
    public static Button four = new Button("4");
    public static Button five = new Button("5");
    public static Button six = new Button("6");
    public static Button seven = new Button("7");
    public static Button eight = new Button("8");
    public static Button nine = new Button("9");
    public static Button zero = new Button("0");

    public static Button plus = new Button("+");
    public static Button minus = new Button("-");
    public static Button multi = new Button("*");
    public static Button davide = new Button("/");
    public static Button equal = new Button("=");

    public static TextField result = new TextField();

    @Override
    public void start(Stage stage) throws IOException {

        HBox hBox = new HBox();
        //hBox.getChildren().add(result);
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(one,four,seven,zero);
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(two,five,eight,equal);
        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(tree,six,nine);
        VBox vBox4 = new VBox();
        vBox4.getChildren().addAll(plus,minus,multi,davide);
        VBox vBox11 = new VBox();
        vBox11.getChildren().addAll(result,hBox);


        Scene scene = new Scene(vBox11, 320, 240);
        hBox.getChildren().addAll(vBox1,vBox2,vBox3,vBox4);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();

        CalculatorEventHandler calculatorEventHandler = new CalculatorEventHandler();
        one.setOnAction(calculatorEventHandler);
        two.setOnAction(calculatorEventHandler);
        tree.setOnAction(calculatorEventHandler);
        four.setOnAction(calculatorEventHandler);
        five.setOnAction(calculatorEventHandler);
        six.setOnAction(calculatorEventHandler);
        seven.setOnAction(calculatorEventHandler);
        eight.setOnAction(calculatorEventHandler);
        nine.setOnAction(calculatorEventHandler);
        zero.setOnAction(calculatorEventHandler);
        plus.setOnAction(calculatorEventHandler);
        minus.setOnAction(calculatorEventHandler);
        multi.setOnAction(calculatorEventHandler);
        davide.setOnAction(calculatorEventHandler);
        equal.setOnAction(calculatorEventHandler);
    }

    public static void main(String[] args) {
        launch();
    }
}
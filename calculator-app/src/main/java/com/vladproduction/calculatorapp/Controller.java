package com.vladproduction.calculatorapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_0;

    @FXML
    private Button btn_1;

    @FXML
    private Button btn_2;

    @FXML
    private Button btn_3;

    @FXML
    private Button btn_4;

    @FXML
    private Button btn_5;

    @FXML
    private Button btn_6;

    @FXML
    private Button btn_7;

    @FXML
    private Button btn_8;

    @FXML
    private Button btn_9;

    @FXML
    private Button clear_btn;

    @FXML
    private Button comma_btn;

    @FXML
    private Button devidion_btn;

    @FXML
    private Button equal_btn;

    @FXML
    private Label label_field;

    @FXML
    private Button minus_btn;

    @FXML
    private Button multy_btn;

    @FXML
    private Button plus_btn;

    @FXML
    private Button plus_minus_btn;

    @FXML
    private Button procent_btn;

    private String str_num = "";
    private float first_num;
    private char operation;

    @FXML
    void initialize() {
        btn_0.setOnAction(actionEvent -> {
            addNumber(0);
        });
        btn_1.setOnAction(actionEvent -> {
            addNumber(1);
        });
        btn_2.setOnAction(actionEvent -> {
            addNumber(2);
        });
        btn_3.setOnAction(actionEvent -> {
            addNumber(3);
        });
        btn_4.setOnAction(actionEvent -> {
            addNumber(4);
        });
        btn_5.setOnAction(actionEvent -> {
            addNumber(5);
        });
        btn_6.setOnAction(actionEvent -> {
            addNumber(6);
        });
        btn_7.setOnAction(actionEvent -> {
            addNumber(7);
        });
        btn_8.setOnAction(actionEvent -> {
            addNumber(8);
        });
        btn_9.setOnAction(actionEvent -> {
            addNumber(9);
        });

        //functions:
        devidion_btn.setOnAction(actionEvent -> {
            mathAction('/');
        });
        multy_btn.setOnAction(actionEvent -> {
            mathAction('*');
        });
        minus_btn.setOnAction(actionEvent -> {
            mathAction('-');
        });
        plus_btn.setOnAction(actionEvent -> {
            mathAction('+');
        });

        equal_btn.setOnAction(actionEvent -> {
            if(this.operation == '+' || this.operation == '-' || this.operation == '*' || this.operation == '/' ){
                equalMethod();
            }
        });

        comma_btn.setOnAction(event -> {
            if(!this.str_num.contains(".")){
                this.str_num += ".";
                label_field.setText(str_num);
            }
        });

        procent_btn.setOnAction(event -> {
            //todo calculate percentage
            if(this.str_num != ""){
                float num = Float.parseFloat(this.str_num) * 0.1f;
                this.str_num = Float.toString(num);
                label_field.setText(str_num);
            }
        });

        plus_minus_btn.setOnAction(event -> {
            //todo -/+ calculation
            if(this.str_num != ""){
                float num = Float.parseFloat(this.str_num) * -1;
                this.str_num = Float.toString(num);
                label_field.setText(str_num);
            }
        });

        clear_btn.setOnAction(event -> {
            label_field.setText("0");
            this.str_num = "";
            this.first_num = 0;
            this.operation = 'A';
        });
    }

    private void equalMethod() {
        float res = 0;
        switch (this.operation){
            case '+':
                res = first_num + Float.parseFloat(this.str_num);
                break;
            case '-':
                res = first_num - Float.parseFloat(this.str_num);
                break;
            case '*':
                res = first_num * Float.parseFloat(this.str_num);
                break;
            case '/':
                if(Float.parseFloat(this.str_num) != 0){
                    res = first_num / Float.parseFloat(this.str_num);
                }else {
                    res = 0;
                }
                break;
        }
        label_field.setText(Float.toString(res));
        this.str_num = "";
        this.operation = 'A';
        this.first_num = 0;
    }

    private void mathAction(char operation) {
        if(this.operation != '+' && this.operation != '-' && this.operation != '*' && this.operation != '/' ){
            this.first_num = Float.parseFloat(this.str_num);
            label_field.setText(String.valueOf(operation));
            this.str_num = "";
            this.operation = operation;
        }
    }

    private void addNumber(int number) {
        this.str_num += Integer.toString(number);
        label_field.setText(str_num);
    }

}

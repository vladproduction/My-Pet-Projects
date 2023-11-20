package com.example.calculator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CalculatorEventHandler implements EventHandler {
    private String InsertText="";

    @Override
    public void handle(Event event) {
        if (InsertText.contains("=")){
            InsertText="";
        }
        Button activeButton = (Button)event.getSource();
        String textButton = activeButton.getText();
        InsertText=InsertText+textButton;
        HelloApplication.result.setText(InsertText);

        if ("=".equals(textButton)) {
            if (InsertText.contains("+")) {
                int plusIndex = InsertText.indexOf("+");
                String firstDigit = InsertText.substring(0, plusIndex);
                String secondDigit = InsertText.substring(plusIndex + 1, InsertText.length() - 1);
                int a = Integer.parseInt(firstDigit);
                int b = Integer.parseInt(secondDigit);
                int sum = a + b;
                InsertText=InsertText+sum;
                HelloApplication.result.setText(InsertText);
            }
            if (InsertText.contains("-")) {
                int plusIndex = InsertText.indexOf("-");
                String firstDigit = InsertText.substring(0, plusIndex);
                String secondDigit = InsertText.substring(plusIndex + 1, InsertText.length() - 1);
                int a = Integer.parseInt(firstDigit);
                int b = Integer.parseInt(secondDigit);
                int sum = a - b;
                InsertText=InsertText+sum;
                HelloApplication.result.setText(InsertText);
            }
            if (InsertText.contains("*")) {
                int plusIndex = InsertText.indexOf("*");
                String firstDigit = InsertText.substring(0, plusIndex);
                String secondDigit = InsertText.substring(plusIndex + 1, InsertText.length() - 1);
                int a = Integer.parseInt(firstDigit);
                int b = Integer.parseInt(secondDigit);
                int sum = a * b;
                InsertText=InsertText+sum;
                HelloApplication.result.setText(InsertText);
            }
            if (InsertText.contains("/")) {
                int plusIndex = InsertText.indexOf("/");
                String firstDigit = InsertText.substring(0, plusIndex);
                String secondDigit = InsertText.substring(plusIndex + 1, InsertText.length() - 1);
                int a = Integer.parseInt(firstDigit);
                int b = Integer.parseInt(secondDigit);
                int sum = a / b;
                InsertText=InsertText+sum;
                HelloApplication.result.setText(InsertText);
            }
        }
    }
}

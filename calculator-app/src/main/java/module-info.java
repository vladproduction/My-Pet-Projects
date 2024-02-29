module com.vladproduction.calculatorapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vladproduction.calculatorapp to javafx.fxml;
    exports com.vladproduction.calculatorapp;
}
module production.calculatorFX {
    requires javafx.controls;
    requires javafx.fxml;


    opens production.calculatorFX to javafx.fxml;
    exports production.calculatorFX;
}
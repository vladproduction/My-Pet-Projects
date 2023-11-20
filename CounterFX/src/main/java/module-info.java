module production.counterFX {
    requires javafx.controls;
    requires javafx.fxml;


    opens production.counterFX to javafx.fxml;
    exports production.counterFX;
}
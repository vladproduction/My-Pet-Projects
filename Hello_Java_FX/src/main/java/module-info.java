module production.helloFX {
    requires javafx.controls;
    requires javafx.fxml;


    opens production.helloFX to javafx.fxml;
    exports production.helloFX;
}
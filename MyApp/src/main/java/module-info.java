module com.example.myapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.myapp to javafx.fxml;
    exports com.example.myapp;
}
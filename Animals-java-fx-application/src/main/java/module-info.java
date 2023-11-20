module com.example.animalsjavafxapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;


    opens com.example.animalsjavafxapplication to javafx.fxml;
    exports com.example.animalsjavafxapplication;
}
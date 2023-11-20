module com.example.signinregisterapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.desktop;
    requires java.sql;


    opens com.example.signinregisterapp to javafx.fxml;
    exports com.example.signinregisterapp;
}
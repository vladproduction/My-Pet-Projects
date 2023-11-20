module com.example.managementwindow {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.managementwindow to javafx.fxml;
    exports com.example.managementwindow;
}
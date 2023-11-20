module com.example.appintro3slides {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.appintro3slides to javafx.fxml;
    exports com.example.appintro3slides;
}
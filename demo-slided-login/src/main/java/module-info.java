module com.example.demoslidedlogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens com.example.demoslidedlogin to javafx.fxml;
    exports com.example.demoslidedlogin;
}

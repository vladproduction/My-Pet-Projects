module com.example.muliview {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.multiview to javafx.fxml;
    exports com.example.multiview;
}
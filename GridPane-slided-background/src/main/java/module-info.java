module com.example.gridpaneslidedbackground {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.gridpaneslidedbackground to javafx.fxml;
    exports com.example.gridpaneslidedbackground;
}
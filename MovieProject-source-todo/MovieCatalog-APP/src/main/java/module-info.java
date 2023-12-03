module com.example.moviecatalogapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires MovieCatalog;


    opens com.example.moviecatalogapp to javafx.fxml;
    exports com.example.moviecatalogapp;
}
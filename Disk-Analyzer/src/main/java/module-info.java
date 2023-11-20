module letscode.diskanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens vladproduction.com to javafx.fxml;
    exports vladproduction.com;
}
module com.example.temperatureschedulefxchart {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.temperatureschedulefxchart to javafx.fxml;
    exports com.example.temperatureschedulefxchart;
}
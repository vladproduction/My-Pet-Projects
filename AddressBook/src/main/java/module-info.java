module com.example.demo_adressbook {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;
}
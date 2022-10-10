module com.example.javasqlbykamilwadowski {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javasqlbykamilwadowski2 to javafx.fxml;
    exports com.example.javasqlbykamilwadowski2;
    exports com.example.javasqlbykamilwadowski2.controllers;
    opens com.example.javasqlbykamilwadowski2.controllers to javafx.fxml;
}
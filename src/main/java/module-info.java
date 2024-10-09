module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    //exports com.example.demo.controller;
    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.controller.Admin;
    opens com.example.demo.controller.Admin to javafx.fxml;
    exports com.example.demo.controller.Mesero;
    opens com.example.demo.controller.Mesero to javafx.fxml;


    opens com.example.demo.Model to javafx.base; // Abrir el paquete para JavaFX

}
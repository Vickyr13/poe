package com.example.demo.controller;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class OrdenesController {

    @FXML
    private ComboBox<String> Cobo1;

    @FXML
    private ComboBox<String> Combo2;

    @FXML
    public void initialize() {
        // Agregar elementos a los ComboBox
        Cobo1.getItems().addAll("Año", "Mes", "Semana");
        Combo2.getItems().addAll("Mesero", "Cocina", "Admin");
    }

}

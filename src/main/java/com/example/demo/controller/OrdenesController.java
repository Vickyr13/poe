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
        Cobo1.getItems().addAll("Mes", "Semana", "Año");
        Combo2.getItems().addAll("Mesero", "Cocina", "Admin");
    }

}

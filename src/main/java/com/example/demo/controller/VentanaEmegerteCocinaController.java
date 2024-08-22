package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class VentanaEmegerteCocinaController {


    @FXML
    private ComboBox<String> comboxEnsalada;

    @FXML
    private ComboBox<String> comboxTortia;

    @FXML
    private ComboBox<String> comboxBebida;


    @FXML
    private void initialize() {

        //se agregan los items
        comboxEnsalada.getItems().addAll("Panzanella italiana", "Ensalada Waldorf", "Ensalada casual");
        comboxTortia.getItems().addAll("Tortia frita", "Tortia tostada", "Tortia caliente");
        comboxBebida.getItems().addAll("Nada", "Agua", "Fresco", "Soda");
    }


}

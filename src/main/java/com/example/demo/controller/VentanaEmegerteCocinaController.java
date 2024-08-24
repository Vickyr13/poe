
package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class VentanaEmegerteCocinaController {
    @FXML
    private ComboBox<String> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private ComboBox<String> combo3;
    @FXML
    private ComboBox<String> combo4;
    @FXML
    private ComboBox<String> combo5;

    @FXML
    private void initialize() {
        //se agregan los items
        combo1.getItems().addAll("items1", "items2", "items3");
        combo2.getItems().addAll("items1", "items2", "items3");
        combo3.getItems().addAll("items1", "items2", "items3");
        combo4.getItems().addAll("items1", "items2", "items3");
        combo5.getItems().addAll("items1", "items2", "items3");
    }
}

package com.example.demo.controller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AdminFormEmpleadosController {

    @FXML
    private RadioButton RdB_Mesero;

    @FXML
    private RadioButton RdB_Cocinero;

    @FXML
    private RadioButton RdB_Repartidor;

    public void initialize() {

        ToggleGroup group = new ToggleGroup();

        RdB_Mesero.setToggleGroup(group);
        RdB_Cocinero.setToggleGroup(group);
        RdB_Repartidor.setToggleGroup(group);
    }

}
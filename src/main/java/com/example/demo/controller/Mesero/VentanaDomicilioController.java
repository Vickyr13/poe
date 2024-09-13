package com.example.demo.controller.Mesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

public class VentanaDomicilioController {

    @FXML
    private Button atras;
    public void but_atras(ActionEvent actionEvent) {

                Stage stage = (Stage) atras.getScene().getWindow();
                stage.close();

    }
}

package com.example.demo.controller.Mesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPedidoController {
    public Button but_ok;
    @FXML
    private Button atras;


    public void but_atras(ActionEvent actionEvent) {
        Stage stage = (Stage) atras.getScene().getWindow();
        stage.close();
    }

    public void but_ok(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) but_ok.getScene().getWindow();
        stage.close();

    }
}

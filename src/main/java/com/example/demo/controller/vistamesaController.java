package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;


public class vistamesaController {

    @FXML
    private Pane mesa5;
    @FXML
    private Button but_Domicilio;

    public void mesa5(MouseEvent mouseEvent) {

        try {
            // Cargar el archivo FXML para la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/VentanaEmegerteCocina.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage
            Stage newStage = new Stage();

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            // Hacer que la nueva ventana aparezca encima de la ventana actual
            newStage.initOwner(mesa5.getScene().getWindow());
            newStage.initModality(Modality.WINDOW_MODAL); // Esto bloquea la ventana principal hasta que la nueva se cierre

            // Mostrar la nueva ventana
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void but_Domicilio() {
        try {
            // Cargar el archivo FXML para la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/ventanaDomicilio.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage
            Stage newStage = new Stage();

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            newStage.setScene(scene);

            // Hacer que la nueva ventana aparezca encima de la ventana actual
            newStage.initOwner(but_Domicilio.getScene().getWindow());
            newStage.initModality(Modality.WINDOW_MODAL); // Esto bloquea la ventana principal hasta que la nueva se cierre

            // Mostrar la nueva ventana
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


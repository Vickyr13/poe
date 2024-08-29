package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;

public class vistapedidoController {
    @FXML
    private Button but_pasarvista;



    public void but_Pasarvista(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/ventanaEmegerteCocina.fxml"));
            Parent root = loader.load();

            // Crear un nuevo Stage para la ventana emergente
            Stage newStage = new Stage();
            Scene scene = new Scene(root);

            newStage.setScene(scene);

            // Mostrar la nueva ventana encima de la actual
            newStage.initOwner(but_pasarvista.getScene().getWindow()); // Establece el propietario como la ventana actual
            newStage.initModality(Modality.APPLICATION_MODAL); // Hace que la ventana sea modal (bloquea la interacción con la ventana principal)
            newStage.showAndWait(); // Espera a que la ventana se cierre antes de volver al control de la ventana principal

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void but_menu(MouseEvent mouseEvent) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/vistamesa.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) but_pasarvista.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


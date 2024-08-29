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
private  Button pagar;

    public void Mesa5(MouseEvent mouseEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com.example.demo.controller.vistamesa.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) pagar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);


            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


@FXML
    public void but_pagar() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/vista-pedido.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) pagar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


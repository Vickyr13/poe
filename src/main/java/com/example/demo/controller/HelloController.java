package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.IOException;


public class HelloController {

    @FXML
    public Button but_IniciarSesion;

    @FXML
    public PasswordField tex_contra;

    @FXML
    public TextField tex_usuario;


    public void but_IniciarSesion(ActionEvent event) {
        String usuarioIngresado = tex_usuario.getText();
        String contraseñaIngresada = tex_contra.getText();

        // Validación de campos vacíos
        if (usuarioIngresado.isEmpty() || contraseñaIngresada.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Por favor ingresa usuario y contraseña");
            return;
        }

        if ((usuarioIngresado.equals("root") && contraseñaIngresada.equals("123"))) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/AdminDashboard.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) but_IniciarSesion.getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
               // stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.");
        }
    }
}
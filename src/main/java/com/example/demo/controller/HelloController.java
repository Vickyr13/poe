package com.example.demo.controller;

import com.example.demo.database.conneection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            System.out.println("Por favor ingresa usuario y contraseña");
            return;
        }



        if (validar(usuarioIngresado, contraseñaIngresada)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/AdminDashboard.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) but_IniciarSesion.getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }


    private boolean validar(String user, String password){
        //Aquí se haría la validación de contraseña
        Connection conn = conneection.getConnection();
        String sql = "SELECT * FROM loggeo WHERE usename = ? AND pass = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}
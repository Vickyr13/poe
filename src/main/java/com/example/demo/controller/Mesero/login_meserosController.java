package com.example.demo.controller.Mesero;

import com.example.demo.database.conneection;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class login_meserosController {

    @FXML
    private Button but_Continuar;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btn0;
    @FXML
    private ImageView miImagen;
    @FXML
    private PasswordField pwrdContra;
    @FXML
    private ImageView imgContinuar;
    @FXML
    private ImageView imgRetroceder;

    @FXML
    public void initialize() {

        pwrdContra.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,4}")) {
                return change;
            } else {
                return null;
            }
        }));
    }

    public void Pucharbtn1(ActionEvent actionEvent) {
        pwrdContra.appendText("1");
    }

    public void pucharbtn2(ActionEvent actionEvent) {
        pwrdContra.appendText("2");
    }

    public void pucharbtn3(ActionEvent actionEvent) {
        pwrdContra.appendText("3");
    }

    public void pucharbtn4(ActionEvent actionEvent) {
        pwrdContra.appendText("4");
    }

    public void pucharbtn5(ActionEvent actionEvent) {
        pwrdContra.appendText("5");
    }

    public void pucharbtn6(ActionEvent actionEvent) {
        pwrdContra.appendText("6");
    }

    public void pucharbtn7(ActionEvent actionEvent) {
        pwrdContra.appendText("7");
    }

    public void pucharbtn8(ActionEvent actionEvent) {
        pwrdContra.appendText("8");
    }

    public void pucharbtn9(ActionEvent actionEvent) {
        pwrdContra.appendText("9");
    }

    public void pucharbtn0(ActionEvent actionEvent) {
        pwrdContra.appendText("0");
    }

    public void pucharCancelar(MouseEvent mouseEvent) {
        pwrdContra.deletePreviousChar();
    }


    //metodo para ir a distintas vistas
    public void imgContinuar(MouseEvent mouseEvent) {
        try {
            String pin = pwrdContra.getText();
            String ruta = tenerRuta();

            if (!validarContrasena()) {
                JOptionPane.showMessageDialog(null, "Rango de Credencial Invalida");
                pwrdContra.clear();                return;
            } else if(!validarPinBDD(pin)) {
                JOptionPane.showMessageDialog(null, "Credencial Invalida");
                pwrdContra.clear();
            }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
                Parent root = loader.load();

                Stage stage = (Stage) imgContinuar.getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                // stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
                stage.centerOnScreen();

                stage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //redirigir segun el id_rol obtenido
    public String tenerRuta() {
        String pin = pwrdContra.getText();
        String rol = obtenerRol(pin);

        if (rol.equals("1")) {
            return "/com/example/demo/views/Mesero/vistamesa.fxml";
        } else if (rol.equals("2")) {
            return "/com/example/demo/views/Mesero/vista-cocina.fxml";
        } else if (rol.equals("3")) {
            return "/com/example/demo/views/Admin/hello-view.fxml";
        } else {
            return "0";
        }
    }
    //validar que existe el pin ingresado
    private boolean validarPinBDD(String pin) {
        Connection conn = conneection.getConnection();
        String sql = "SELECT * FROM empleados WHERE pin = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pin);
            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //metodo para obtener el rol y verificar el rol para redirigir a vistas correctas
    public String obtenerRol(String pin) {
        // Conexión a la base de datos
        Connection conn = conneection.getConnection();

        // Consulta SQL que valida el pin y obtiene el rol
        String sql = "SELECT id_rol FROM empleados WHERE pin = ?";

        try {
            // Preparar la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pin);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("id_rol");
            } else {
                return "0";  // Retornar "0" si no se encuentra el rol
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //validar campo paswords
    public boolean validarContrasena() {
        if (pwrdContra.getText().length() < 4) {
            return false;
        } else {
            return true;        }

    }
}
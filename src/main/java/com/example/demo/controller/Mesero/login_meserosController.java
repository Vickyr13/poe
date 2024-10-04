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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(tenerRuta(pwrdContra.getText())));
            Parent root = loader.load();

            Stage stage = (Stage) imgContinuar.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            //  stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //odtener y validar la contraseña
    public String tenerRuta(String IDContra) {

        if (IDContra.equals("000") || (validar(IDContra, 1))) {
            return "/com/example/demo/views/Mesero/vistamesa.fxml";
        } else if (IDContra.equals("111") || validar(IDContra, 3)) {
            return "/com/example/demo/views/Admin/hello-view.fxml";
        } else if (IDContra.equals("222") || validar(IDContra, 2)) {
            return "/com/example/demo/views/Mesero/vista-cocina.fxml";
        } else {

            new Alert(Alert.AlertType.ERROR, "La contraseña está mal.").showAndWait();

            return "";
        }
    }

    private boolean validar(String pin, int rol) {
        // Conexión a la base de datos
        Connection conn = conneection.getConnection();

        // Consulta SQL que valida tanto el pin como el rol
        String sql = "SELECT * FROM empleados WHERE pin = ? AND id_rol = ?";

        try {
            // Preparar la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pin);  // Setear el pin
            stmt.setInt(2, rol);     // Setear el rol

            // Ejecutar la consulta
            ResultSet resultSet = stmt.executeQuery();

            // Si se encuentra una fila, es que el pin y el rol coinciden
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
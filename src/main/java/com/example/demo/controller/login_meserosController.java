package com.example.demo.controller;

import com.example.demo.HelloApplication;
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
import org.w3c.dom.Node;

import java.awt.*;
import java.io.IOException;
import java.util.EventObject;

public class login_meserosController {

    @FXML
    private static Button btnContinuar;
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

    public void initialize() {
        pwrdContra.setVisible(true);
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

    public void pucharContinuar(MouseEvent mouseEvent) throws IOException {


        if(pwrdContra.equals("0000")){
            Redireccionar("vistamesa.fxml");
        }


    }

   public static void Redireccionar(String dirección) throws IOException {

       try {
           FXMLLoader loader = new FXMLLoader(login_meserosController.class.getResource("/com/example/demo/views/vista-pedido.fxml"));
           Parent root = loader.load();

           Stage stage = (Stage) btnContinuar.getScene().getWindow();
           Scene scene = new Scene(root);
           stage.setScene(scene);

           stage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

    public void Continuar(ActionEvent actionEvent) throws IOException {
        if(pwrdContra.equals("0000")){
            Redireccionar("vistamesa.fxml");
        }
    }
}

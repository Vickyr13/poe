package com.example.demo.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminFormPlatillo {
    @FXML
    public RadioButton rdoInactivo;
    @FXML
    public ToggleGroup GroupEstado;
    @FXML
    public RadioButton rdoActivo;
    @FXML
    public Button btnVolver;
    @FXML
    public TextArea txaDescripcion;
    @FXML
    public TextField txtPrecio_plato;
    @FXML
    private Button Btn_Agregar_Plato;

    @FXML
    private TextField Precio_plato;

    @FXML
    private TextField Txt_NombreP;

    @FXML
    private ComboBox<String> cboCategoria;

    public void REGRESAR(ActionEvent actionEvent) {
        CambiarVista("AdminProductos");
    }
    public void CambiarVista(String Direccion){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/"+Direccion+".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
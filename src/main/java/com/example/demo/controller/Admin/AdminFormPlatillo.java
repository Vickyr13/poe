package com.example.demo.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
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
    public TextField txtPrecio_producto;
    @FXML
    public Button Btn_Agregar_Plato;
    @FXML
    private TextField Txt_NombreP;
    @FXML
    private ComboBox<String> cboCategoria;






    public void REGRESAR(ActionEvent actionEvent) {
        CambiarVista("AdminProductos");
    }

    public void CambiarVista(String Direccion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/" + Direccion + ".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Btn_Agregar_Plato(ActionEvent actionEvent) {

        //Validar campos
        if (Txt_NombreP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingres el nombre del producto");
            return;
        }
//        if (cboCategoria.getValue() == null) {
//            JOptionPane.showMessageDialog(null, "Seleccione la categoria del producto");
//            return;
//        }



        String precioTexto = txtPrecio_producto.getText().trim();

        if (precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el precio del producto");
            return;
        }

        try {
            double precio_product = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido");
            return;
        }


        if (rdoActivo.isSelected() == false && rdoInactivo.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione el estado del producto");
            return;
        }

        if (txaDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingres la descripcion del producto");
            return;
        }

    }
}
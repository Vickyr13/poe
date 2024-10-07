package com.example.demo.controller.Admin;

import com.example.demo.Model.productos;
import com.example.demo.database.CategoriaDAO;
import com.example.demo.database.productosDAO;
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

    CategoriaDAO querysCategoria = new CategoriaDAO();

    @FXML
    public void initialize() {
        cargarCategorias();
    }

    public void cargarCategorias() {
        cboCategoria.getItems().clear();
        cboCategoria.getItems().addAll(querysCategoria .obtenerCategorias());
    }

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
        productosDAO querys = new productosDAO();
        if(validar()){
            String nombre_Producto = Txt_NombreP.getText();
            double precio_unitario = Double.parseDouble(txtPrecio_producto.getText().trim());
            String descripcion_Producto = txaDescripcion.getText().trim();

            int estado = 0;
            if(rdoActivo.isSelected()){
                estado = 1;
            }if(rdoInactivo.isSelected()){
                estado = 0;
            }
          productos productos = new productos(2, nombre_Producto, precio_unitario,descripcion_Producto,estado);


            try {
                querys.insertarProducto(productos);
            }catch(Exception e){
                System.out.println("Error al insertar productos "+e.getMessage());
            };
        }
    }


    private boolean validar() {

        //Validar campos
        if (Txt_NombreP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingres el nombre del producto");
            return false;
        }


//        if (cboCategoria.getValue() == null) {
//            JOptionPane.showMessageDialog(null, "Seleccione la categoria del producto");
//            return false;
//        }


        String precioTexto = txtPrecio_producto.getText().trim();

        if (precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el precio del producto");
            return false;
        }

        try {
            double precio_product = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido");
            return false;
        }

        if (!rdoActivo.isSelected() && !rdoInactivo.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione el estado del producto");
            return false;
        }

        if (txaDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingres la descripcion del producto");
            return false;
        }
        return true;
    }


}
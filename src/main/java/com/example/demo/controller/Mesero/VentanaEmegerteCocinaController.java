package com.example.demo.controller.Mesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class VentanaEmegerteCocinaController {

    public Spinner<Integer> candidaPlato;
    public Label nombrePlato;
    public Button but_cofirmar;
    public TextArea texMesaje;

    @FXML
    private Button but_cancelar;

    private int id_Producto; 
    private String producto;
    private double sub_Total;


    @FXML
    public void initialize( int idProducto, String Producto, double subTotal) {
        this.id_Producto = idProducto;
        this.producto = Producto;
        this.sub_Total = subTotal;

        SpinnerValueFactory<Integer> valor = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        candidaPlato.setValueFactory(valor);
    }

    public void but_cancelar(ActionEvent actionEvent) {
        // Cierra la ventana actual (emergente)
        Stage stage = (Stage) but_cancelar.getScene().getWindow();
        stage.close();

    }

    public void but_cofirmar(ActionEvent actionEvent) throws SQLException, IOException {

        String[] datos = new String[4];
        datos[0] = texMesaje.getText();
        datos[1] = String.valueOf(candidaPlato.getValue());
        datos[2] = producto;
        datos[3] = String.valueOf(sub_Total);


        if (vistaPedido != null) {
           vistaPedido.addProductToOrder(datos);
        } else {
            System.out.println("Error: Controlador 'vistapedidoController' no inicializado.");
        }

        Stage stage = (Stage) but_cofirmar.getScene().getWindow();
        stage.close();
    }

    private vistapedidoController vistaPedido;

    public void setVistapedidoController(vistapedidoController vista) {
        this.vistaPedido = vista;
    }

}
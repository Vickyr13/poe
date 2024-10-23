package com.example.demo.controller.Mesero;

import com.example.demo.Model.Detalles_ordenes;
import com.example.demo.database.Detalle_ordenesDAO;
import com.example.demo.database.OrdenesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class VentanaEmegerteCocinaController {

    public Spinner<Integer> candidaPlato;
    public Label nombrePlato;
    public Button but_cofirmar;

    @FXML
    private Button but_cancelar;

    private double precioTotal = 0.0;
    private int idProducto;
    private int idOrden;
    private int numeroMesa;
    private double sudtoTotal;


    @FXML
    public void initialize(int idOrden, int idProducto, int numeroMesa, double sudtoTotal) {
        this.idOrden = idOrden;
        this.idProducto = idProducto;
        this.numeroMesa = numeroMesa;
        this.sudtoTotal = sudtoTotal;

        SpinnerValueFactory<Integer> valor = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);

        candidaPlato.setValueFactory(valor);
    }


    public void but_cancelar(ActionEvent actionEvent) {

        // Cierra la ventana actual (emergente)
        Stage stage = (Stage) but_cancelar.getScene().getWindow();
        stage.close();

    }

    public void but_cofirmar(ActionEvent actionEvent) throws SQLException {
        int cantidadPlato = candidaPlato.getValue();

        // guardar los datos en la base de datos
        OrdenesDAO ordenesDAO = new OrdenesDAO();
        Detalle_ordenesDAO detallesOrdenes = new Detalle_ordenesDAO();

        precioTotal = ordenesDAO.totalPrecio(numeroMesa);

        // crear los detalles de la orden y guardarlos
        Detalles_ordenes de = new Detalles_ordenes(idOrden, idProducto, numeroMesa, cantidadPlato, (sudtoTotal*cantidadPlato));
        detallesOrdenes.insertDetallesOrdenes(de);

        Stage stage = (Stage) but_cofirmar.getScene().getWindow();
        stage.close();
    }


}
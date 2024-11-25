package com.example.demo.controller.Mesero;

import com.example.demo.Model.Ordenes;
import com.example.demo.database.OrdenesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VentanaPedidoController {
    public Button but_ok;
    public TableColumn <Ordenes, String> Column_NumeroOrden;
    public TableColumn <Ordenes, String> Column_numeroMesa;
    public TableColumn <Ordenes, Double> Column_precio;
    public TableView <Ordenes> table_ordenesActivas;
    @FXML
    private Button atras;

    @FXML
    public void initialize() throws SQLException {
        llenarTables();
    }

    private void llenarTables() throws SQLException {
        List<Ordenes> ordenes = OrdenesDAO.ordenesActivas();
        table_ordenesActivas.getItems().addAll(ordenes);

        Column_NumeroOrden.setCellValueFactory(new PropertyValueFactory<>("id_orden"));
        Column_numeroMesa.setCellValueFactory(new PropertyValueFactory<>("numeroMesa"));
        Column_precio.setCellValueFactory(new PropertyValueFactory<>("totalDinero"));
    }

    public void but_atras(ActionEvent actionEvent) {
        Stage stage = (Stage) atras.getScene().getWindow();
        stage.close();
    }

    public void but_ok(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) but_ok.getScene().getWindow();
        stage.close();
    }
}

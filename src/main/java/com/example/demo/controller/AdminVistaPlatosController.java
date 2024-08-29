package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AdminVistaPlatosController {

    @FXML
    private AnchorPane CategoriasPAnel;

    @FXML
    private AnchorPane DashboardPanel;

    @FXML
    private AnchorPane OrdenesPanel;

    @FXML
    private AnchorPane ProductosPanel;

    @FXML
    private AnchorPane UsuariosPanel;

    @FXML
    private Button btnAgregar;

    @FXML
    private TableView<String> tableProductos;

    @FXML
    public void initialize() {
    tableProductos.getItems().addAll("id_Producto","Nombre","Descripción","Foto");
    }

}


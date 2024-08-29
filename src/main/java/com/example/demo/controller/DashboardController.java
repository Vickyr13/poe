package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashboardController {

    @FXML
    private AnchorPane CategoriasPAnel;

    @FXML
    private AnchorPane DashboardPanel;

    @FXML
    private AnchorPane OrdenesPanel;

    @FXML
    private AnchorPane PanelORdenesAnulaes;

    @FXML
    private AnchorPane ProductosPanel;

    @FXML
    private AnchorPane UsuariosPanel;

    @FXML
    private ComboBox<String> cboAnos;

    @FXML
    private ComboBox<String> cboMeses;

    @FXML
    private AnchorPane panelIngresoAnual;

    @FXML
    private AnchorPane panelventas;

    @FXML
    private BarChart<?, ?> tablaBarras;

    @FXML
    private PieChart tablaDona1;

    @FXML
    private PieChart tabladona2;

    public void initialize() {
        cboMeses.getItems().addAll("Enero", "Febrero", "Marzo", "Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre", "Noviembre", "Diciembre");
        cboAnos.getItems().addAll("2024","2025","2026","2027");
    }



}






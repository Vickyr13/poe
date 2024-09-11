package com.example.demo.controller.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {

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

    @FXML
    void ClickCategorias(MouseEvent event) {
        CambiarVista("AdminCategorias");
    }

    @FXML
    void ClickDashboard(MouseEvent event) {
        CambiarVista("AdminDashboard");
    }

    @FXML
    void ClickOrdenes(MouseEvent event) {
        CambiarVista("AdminOrdenes");
    }

    @FXML
    void ClickProductos(MouseEvent event) {
        CambiarVista("AdminProductos");
    }

    @FXML
    void ClickUsuarios(MouseEvent event) {
        CambiarVista("AdminUsuarios");
    }

    public void CambiarVista(String Direccion){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/"+Direccion+".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) DashboardPanel.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






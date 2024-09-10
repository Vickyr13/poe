package com.example.demo.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminOrdenesController {

    @FXML
    private Pane CategoriasPanel;

    @FXML
    private ComboBox<String> Cobo1;

    @FXML
    private ComboBox<String> Combo2;

    @FXML
    private Pane DashboardPanel;

    @FXML
    private Pane OrdenesPanel;

    @FXML
    private Pane ProductosPanel;

    @FXML
    private Pane UsuariosPanel;

    @FXML
    public void initialize() {
        Cobo1.getItems().addAll("Mes", "Semana", "Año");
        Combo2.getItems().addAll("Mesero", "Cocina", "Admin");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/"+Direccion+".fxml"));
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

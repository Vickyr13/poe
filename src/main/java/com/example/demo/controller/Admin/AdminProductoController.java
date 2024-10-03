package com.example.demo.controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminProductoController {
    @FXML
    public Button btnEditar;

    @FXML
    public Button btnEliminar;

    @FXML
    private AnchorPane CategoriasPanel;

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
    private TableView<?> tableProductos;

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

    @FXML
    void AbrirForm(ActionEvent event) {
        CambiarVista("AdminFormPlatillo");
    }

    public void CambiarVista(String Dirección){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/"+Dirección+".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) DashboardPanel.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnEditar(ActionEvent actionEvent) {
    }

    public void EditarRegistro(ActionEvent actionEvent) {
    }

    public void EliminarRegistro(ActionEvent actionEvent) {
    }
}







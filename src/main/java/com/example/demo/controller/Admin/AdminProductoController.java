package com.example.demo.controller.Admin;

import com.example.demo.database.conneection;
import com.example.demo.database.productosDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AdminProductoController {
    @FXML
    public Button btnEditar;
    @FXML
    public Button btnEliminar;
    @FXML
    public TableColumn<Map, Object> nombreProducto;
    @FXML
    public TableColumn<Map, Object> nombreCegoria;
    @FXML
    public TableColumn<Map, Object> descripcionProducto;
    @FXML
    public TableColumn<Map, Object> precioTotal;
    @FXML
    public TableColumn<Map, Object> estadoProducto;

    @FXML
    private AnchorPane DashboardPanel;

    @FXML
    private Button btnAgregar;

    @FXML
    private TableView<Map> tableProductos;

   private final productosDAO  productosDAO = new productosDAO();
    private static int id_button;

    @FXML
    public void initialize() throws SQLException {
        llenarTable();
    }

    private String idProducto = null;

    public void llenarTable() throws SQLException {
        ObservableList<Map> lista = productosDAO.getProductos();
        tableProductos.setItems(lista);

        // Configuración de las columnas de la tabla
        nombreProducto.setCellValueFactory(new MapValueFactory<>("nombre_producto"));
        nombreCegoria.setCellValueFactory(new MapValueFactory<>("nombre_categoria"));
        descripcionProducto.setCellValueFactory(new MapValueFactory<>("descripcion"));
        precioTotal.setCellValueFactory(new MapValueFactory<>("precio_unitario"));
        estadoProducto.setCellValueFactory(new MapValueFactory<>("estado_producto"));
    }

    public void CambiarVista(String Dirección) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/" + Dirección + ".fxml"));
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
        Map<String, Object> productoSeleccionado = tableProductos.getSelectionModel().getSelectedItem();

        id_button = 1;  // Aseguramos que estamos en modo edición
        if (productoSeleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/AdminFormPlatillo.fxml"));
                Parent root = loader.load();

                // Obtener el controlador del formulario
                AdminFormPlatillo controller = loader.getController();

                // Obtener el ID del producto seleccionado
                String idProductoSeleccionado = String.valueOf(productoSeleccionado.get("id_producto"));

                // Pasar los datos del producto seleccionado al controlador del formulario
                controller.cargarDatosParaEditar(productoSeleccionado, idProductoSeleccionado);

                // Crear un nuevo Stage (ventana) para la nueva vista
                Stage newStage = new Stage();
                newStage.setTitle("Editar Producto");
                newStage.setScene(new Scene(root));

                // Mostrar la nueva ventana sin cerrar la actual
                newStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
        }
    }

    public void EliminarRegistro(ActionEvent actionEvent) {
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

    @FXML
    void AbrirForm(ActionEvent event) {
        id_button = 2;
        CambiarVista("AdminFormPlatillo");
    }

    public static int getId_button() {
        return id_button;
    }

}
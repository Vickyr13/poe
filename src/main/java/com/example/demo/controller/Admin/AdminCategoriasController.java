package com.example.demo.controller.Admin;

import com.example.demo.Model.Categorias;
import com.example.demo.database.CategoriaDAO;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminCategoriasController {
    @FXML
    public Button btnEditar;
    @FXML
    public Button btnEliminar;

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
    private TableView<Map> tableCategorias; // El TableView donde se mostrarán las categorías
    @FXML
    private TableColumn<Map, Object> columIdCategoria; // Columna para el ID

    @FXML
    private TableColumn<Map, Object> columnCategoria;  // Columna para el nombre de la categoría

    @FXML
    private TableColumn<Map, Object> columnEstado;    // Columna para el estado


    private static int id_button = 1;

    // Método que se llamará para cargar los datos en el TableView
    public void initialize() throws SQLException {
        //    configurarColumnas();
       llenarTable();
    }

    // Metodo Asigna el valor a las columnas
//    private void configurarColumnas() {
//        columIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_cstegoria") );
//        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre_categoria"));
//        columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado_categoria"));
//    }
//     Método que se llamará para llenar el TableView con los datos de la base de datos
    public void llenarTable() throws SQLException {
    ObservableList<Map> lista = CategoriaDAO.getCategoria();
    tableCategorias.setItems(lista);

    // Configuración de las columnas de la tabla
        columIdCategoria.setCellValueFactory(new MapValueFactory<>("id_categoria"));
        columnCategoria.setCellValueFactory(new MapValueFactory<>("nombre_categoria"));
        columnEstado.setCellValueFactory(new MapValueFactory<>("estado_categoria"));
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
            stage.centerOnScreen();
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAgregar(javafx.event.ActionEvent actionEvent) {CambiarVista("AdminFormcategoria");}

    public void AgregarCategoria(ActionEvent actionEvent) {
        id_button = 2;
        CambiarVista("AdminFormCategoria");
    }


    public void EditarCategoria(ActionEvent actionEvent) {
        Map<String, Object> CategoriaSeleccionada = tableCategorias.getSelectionModel().getSelectedItem();

        id_button = 1;  // Aseguramos que estamos en modo edición
        if (CategoriaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/AdminFormCategoria.fxml"));
                Parent root = loader.load();

                // Obtener el controlador del formulario
                AdminFormCategoria controller = loader.getController();

                // Obtener el ID del producto seleccionado
                String idProductoCategoria = String.valueOf(CategoriaSeleccionada.get("id_categoria"));

                // Pasar los datos del producto seleccionado al controlador del formulario
                controller.cargarDatosParaEditarCatego(CategoriaSeleccionada, idProductoCategoria);

                // Crear un nuevo Stage (ventana) para la nueva vista
                Stage newStage = new Stage();
                newStage.setTitle("Editar Categoria");
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

    public static int getIdButton() {
        return id_button;
    }

}

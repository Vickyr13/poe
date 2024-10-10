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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    private TableView<Categorias> tableCategorias; // El TableView donde se mostrarán las categorías
    @FXML
    private TableColumn<Categorias, Integer> columIdCategoria; // Columna para el ID

    @FXML
    private TableColumn<Categorias, String> columnCategoria;  // Columna para el nombre de la categoría

    @FXML
    private TableColumn<Categorias, Integer> columnEstado;    // Columna para el estado

    // Método que se llamará para cargar los datos en el TableView
    public void initialize() {
       configurarColumnas();
      //  cargarCategorias();
    }

    // Metodo Asigna el valor a las columnas
    private void configurarColumnas() {
        //columIdCategoria.setCellValueFactory(new PropertyValueFactory<>("id_cstegoria") );
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre_categoria"));
        columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado_categoria"));
    }
    // Método que se llamará para llenar el TableView con los datos de la base de datos
//    public void cargarCategorias() {
//        //CategoriaDAO categoriaDAO = new CategoriaDAO();
//        try {
//            CategoriaDAO categoriaDAO = new CategoriaDAO();
//            List<Categorias> listaCategorias = categoriaDAO.obtenerCategoriasTable();
//
//            ObservableList<Categorias> data = FXCollections.observableArrayList(listaCategorias);
//            tableCategorias.setItems(data);
//        } catch (SQLException e) {
//            System.err.println("Error al cargar las categorías: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }



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
        CambiarVista("AdminFormCategoria");
    }

    public void EditarCategoria(ActionEvent actionEvent) {
    }

    public void EliminarCategoria(ActionEvent actionEvent) {
    }
}

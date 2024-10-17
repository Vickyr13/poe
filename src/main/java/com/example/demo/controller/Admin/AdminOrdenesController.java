package com.example.demo.controller.Admin;
import com.example.demo.database.OrdenesDAO;
import com.example.demo.database.productosDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class AdminOrdenesController {

    @FXML
    public TableView<Map> tablaOrdener;


    @FXML
    private Pane CategoriasPanel;

    @FXML
    private ComboBox<String> Cobo1;

    @FXML
    private ComboBox<String> Combo2;

    @FXML
    private TableColumn<Map,Object> dinero_ordenes;

    @FXML
    private TableColumn<Map,Object> fecha_ordenes;

    @FXML
    private TableColumn<Map,Object> hora_ordenes;

    @FXML
    private TableColumn<Map,Object> id_ordenes;

    @FXML
    private TableColumn<Map,Object> mesaid;

    @FXML
    private TableColumn<Map,Object> nombreOrdenes;


    @FXML
    private Pane DashboardPanel;

    @FXML
    private Pane OrdenesPanel;

    @FXML
    private Pane ProductosPanel;

    @FXML
    private Pane UsuariosPanel;

    @FXML    public void initialize() throws SQLException {
        Cobo1.getItems().addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        );

        Combo2.getItems().addAll("Semana 1", "Semana 2", "Semana 3");

        llenarTable();
    }

    @FXML
    void ClickCategorias(MouseEvent event) {CambiarVista("AdminCategorias");
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


    public void llenarTable() throws SQLException {
       OrdenesDAO OrdenesDAO = new OrdenesDAO();
        ObservableList<Map> lista = OrdenesDAO.getOrdenes();
        tablaOrdener.setItems(lista);

        // Configuración de las columnas de la tabla
        nombreOrdenes.setCellValueFactory(new MapValueFactory<>("nombre_producto"));
        id_ordenes.setCellValueFactory(new MapValueFactory<>("id_orden"));
        hora_ordenes.setCellValueFactory(new MapValueFactory<>("fecha_orden"));
        mesaid.setCellValueFactory(new MapValueFactory<>("numero_mesa"));
        dinero_ordenes.setCellValueFactory(new MapValueFactory<>("sub_total"));
    }
}

package com.example.demo.controller.Admin;

import com.example.demo.database.EmpleadoDAO;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class AdminUsuariosController {

    @FXML
    public Button btnAgregar;

    @FXML
    public Button btnEditar;

    @FXML
    public Button btnEliminar;

    @FXML
    private Pane CategoriasPanel;

    @FXML
    private Pane DashboardPanel;

    @FXML
    private Pane OrdenesPanel;

    @FXML
    private Pane ProductosPanel;

    @FXML
    private Pane UsuariosPanel;

    @FXML
    private Pane backPanel;

    @FXML
    private TableView<Map> tablaEmpleados;


    @FXML
    private TableColumn<Map, Object> apellido;
    @FXML
    private TableColumn<Map, Object> cargo;

    @FXML
    private TableColumn<Map, Object> contraro;

    @FXML
    private TableColumn<Map, Object> dui;

    @FXML
    private TableColumn<Map, Object> estado;

    @FXML
    private TableColumn<Map, Object> mail;

    @FXML
    private TableColumn<Map, Object> nombre;

    @FXML
    private TableColumn<Map, Object> tel;
    @FXML
    private TableColumn<Map, Object> pin;

    @FXML
    public void initialize() throws SQLException {
        llenarTable();
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

    public void btnAgregar(ActionEvent actionEvent) {CambiarVista("AdminFormEmpleados");}

    public void btnEditar(ActionEvent actionEvent) {
    }

    public void EliminarEmpleado(ActionEvent actionEvent) {
    }

    public void llenarTable() throws SQLException {
        EmpleadoDAO empleadosDao = new EmpleadoDAO();
        ObservableList<Map> lista = empleadosDao.getEmpleados();
        tablaEmpleados.setItems(lista);

        // Configuración de las columnas de la tabla
        nombre.setCellValueFactory(new MapValueFactory<>("nombre_empleado"));
        apellido.setCellValueFactory(new MapValueFactory<>("apellido_empleado"));
        dui.setCellValueFactory(new MapValueFactory<>("dui"));
        mail.setCellValueFactory(new MapValueFactory<>("email"));
        tel.setCellValueFactory(new MapValueFactory<>("telefono"));
        contraro.setCellValueFactory(new MapValueFactory<>("fecha_contratacion"));
        cargo.setCellValueFactory(new MapValueFactory<>("r.nombre_rol"));
        estado.setCellValueFactory(new MapValueFactory<>("estado_empleado"));
        pin.setCellValueFactory(new MapValueFactory<>("pin"));




    }

}

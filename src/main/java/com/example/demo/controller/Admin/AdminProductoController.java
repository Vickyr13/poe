package com.example.demo.controller.Admin;

import com.example.demo.database.conneection;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

    private static final String COLUMN_ID_PRODUCTOS = "Id_productos";
    private static final String COLUMN_ID_CATEGORIA = "id_categoria";
    private static final String COLUMN_NOMBRE_PRODUCTO = "nombre_producto"; // Cambiado a minúscula
    private static final String COLUMN_PRECIO_UNITARIO = "precio_unitario";
    private static final String COLUMN_DESCRIPCION_PRODUCTO = "descripcion"; // Cambiado a "descripcion"
    private static final String COLUMN_ESTADO_PRODUCTO = "estado_producto";

    @FXML
    public void initialize() throws SQLException {
        llenarTable();
    }

    public static ObservableList<Map> getProduct() {
        var sql = "SELECT * FROM productos";
        ObservableList<Map> list = FXCollections.observableArrayList();
        try {
            Connection con = conneection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put(COLUMN_ID_PRODUCTOS, rs.getInt("id_producto")); // Cambiado a "id_producto"
                m.put(COLUMN_ID_CATEGORIA, rs.getInt("id_categoria"));
                m.put(COLUMN_NOMBRE_PRODUCTO, rs.getString("nombre_producto")); // Cambiado a "nombre_producto"
                m.put(COLUMN_PRECIO_UNITARIO, rs.getDouble("precio_unitario"));
                m.put(COLUMN_DESCRIPCION_PRODUCTO, rs.getString("descripcion")); // Cambiado a "descripcion"
                m.put(COLUMN_ESTADO_PRODUCTO, rs.getString("estado_producto")); // Se mantiene, aunque se debería ajustar el tipo a int
                list.add(m);
            }
          //  con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void llenarTable() {
        ObservableList<Map> lista = getProduct();
        tableProductos.setItems(lista);
        nombreProducto.setCellValueFactory(new MapValueFactory(COLUMN_NOMBRE_PRODUCTO));
        nombreCegoria.setCellValueFactory(new MapValueFactory(COLUMN_ID_CATEGORIA));
        descripcionProducto.setCellValueFactory(new MapValueFactory(COLUMN_DESCRIPCION_PRODUCTO));
        precioTotal.setCellValueFactory(new MapValueFactory(COLUMN_PRECIO_UNITARIO));
        estadoProducto.setCellValueFactory(new MapValueFactory(COLUMN_ESTADO_PRODUCTO));
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
        CambiarVista("AdminFormPlatillo");
    }
}

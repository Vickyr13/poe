package com.example.demo.controller.Mesero;

import com.example.demo.Model.Ordenes;
import com.example.demo.Model.productos;
import com.example.demo.database.CategoriaDAO;
import com.example.demo.database.OrdenesDAO;
import com.example.demo.database.conneection;
import com.example.demo.database.productosDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class vistapedidoController {
    public Label label_precioTotal;
    @FXML
    private Button but_pasarvista;
    @FXML
    private TextField txt_buscar;

    @FXML
    private Button bt_buscar;

    @FXML
    private ComboBox<String> cboCategoria;

    //----------------------------------------------------------------

    @FXML
    private TableColumn<productos, String> columm_descripcion;

    @FXML
    private TableColumn<productos, Double> columm_precioUnitario;

    @FXML
    private TableColumn<productos, String> columm_producto;

    @FXML
    private TableView<productos> tableView;

    @FXML
    private TableColumn<Map, Object> columm_cantidad;

    @FXML
    private TableColumn<Map, Object> columm_productoPedido;

    @FXML
    private TableColumn<Map, Object> columm_subTotal;

    @FXML
    private TableView<Map> table_pedidos;

    @FXML
    private Label label_numeroMesa;
    private int nunMesa;
    private int idOrden = -1;
    private int id_producto;

    //----------------------------------------------------------------


    CategoriaDAO querysCategoria = new CategoriaDAO();

    @FXML
    public void initialize(String numeromesa) throws SQLException {
        nunMesa = Integer.parseInt(numeromesa);
        label_numeroMesa.setText(numeromesa);

        idOrden = OrdenesDAO.obtenerOrdenActivaPorMesa(nunMesa);

        cargarCategorias();
        llenarTabla();
        llenarTable_Odenes();
       precioTotal();
    }

    // llenar tabla
    public void llenarTabla() throws SQLException {
        List<productos> venta = productosDAO.productoBenta();


        tableView.getItems().addAll(venta);

        columm_producto.setCellValueFactory(new PropertyValueFactory<>("nombre_Producto"));
        columm_descripcion.setCellValueFactory(new PropertyValueFactory<>("descriccios_Producto"));
        columm_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
    }

    public void llenarTable_Odenes() throws SQLException {
        ObservableList<Map> lista = OrdenesDAO.datosOrden(nunMesa);
        table_pedidos.setItems(lista);

        columm_cantidad.setCellValueFactory(new MapValueFactory<>("cantidad"));
        columm_productoPedido.setCellValueFactory(new MapValueFactory<>("nombre_producto"));
        columm_subTotal.setCellValueFactory(new MapValueFactory<>("sub_total"));

    }


    private void precioTotal() throws SQLException {
        OrdenesDAO ordenDAO = new OrdenesDAO();
        ordenDAO.totalPrecio(nunMesa);
        label_precioTotal.setText("Precio Total: $" + OrdenesDAO.totalPrecio(nunMesa));
    }


    public void cargarCategorias() {
        cboCategoria.getItems().clear();
        cboCategoria.getItems().addAll(querysCategoria.obtenerCategoriasFiltradas());
    }


    public void but_Pasarvista(ActionEvent actionEvent) throws SQLException {
        try {
            productos productoSeleccionado = tableView.getSelectionModel().getSelectedItem();
            // validar la linea
            if(productoSeleccionado == null){
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
                return;
            }

            // Cargar el FXML de la ventana emergente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/ventanaEmegerteCocina.fxml"));
            Parent root = loader.load();

            VentanaEmegerteCocinaController ventnanaEmegerteCocina = loader.getController();

            // se crea una orden  o no bueno si existe si no, no
            if (idOrden == -1) {
                OrdenesDAO ordenDAO = new OrdenesDAO();
                Ordenes nuevaOrden = new Ordenes(0.0, "Activo");
                idOrden = ordenDAO.insertOrden(nuevaOrden);
            }
            id_producto = productoSeleccionado.getId_productos();

            double valor = productoSeleccionado.getPrecio_unitario();
            ventnanaEmegerteCocina.initialize( idOrden, id_producto, nunMesa, valor);


            // Crear y mostrar la nueva ventana
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.initOwner(but_pasarvista.getScene().getWindow());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();

            llenarTable_Odenes();
            precioTotal();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void but_menu(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/vistamesa.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) but_pasarvista.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cboCategoria(ActionEvent actionEvent) {
    }

    public void bt_buscar(ActionEvent actionEvent) throws SQLException {
        String producto = txt_buscar.getText();;

        productosDAO produc = new productosDAO();;
        produc.BuscarProductoFiltrado(producto);
    }
}



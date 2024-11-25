package com.example.demo.controller.Mesero;

import com.example.demo.Model.Comanda; // Asegúrate de que esta clase existe y tiene los atributos necesarios
import com.example.demo.database.CocinaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class VistaCocinaController {
    @FXML
    private Button BtnFinalizar01, BtnFinalizar02, BtnFinalizar03, BtnFinalizar04;

    @FXML
    private Label NumMesa01, NumMesa02, NumMesa03, NumMesa04;

    // Tablas para mostrar las órdenes
    @FXML
    private TableView<Comanda> TableOrden01, TableOrden02, TableOrden03, TableOrden04;

    // Instancia del DAO para interactuar con la base de datos
    private CocinaDAO cocinaDAO = new CocinaDAO();

    /**
     * Inicializa la vista. Configura las tablas y carga las órdenes.
     */
    @FXML
    private void initialize() {
        // Configuración de columnas para cada tabla
        configureTableColumns(TableOrden01);
        configureTableColumns(TableOrden02);
        configureTableColumns(TableOrden03);
        configureTableColumns(TableOrden04);

        // Carga las órdenes desde la base de datos
        try {
            loadOrdenes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Configura los botones para finalizar las órdenes
        BtnFinalizar01.setOnAction(e -> finalizarOrden(TableOrden01));
        BtnFinalizar02.setOnAction(e -> finalizarOrden(TableOrden02));
        BtnFinalizar03.setOnAction(e -> finalizarOrden(TableOrden03));
        BtnFinalizar04.setOnAction(e -> finalizarOrden(TableOrden04));
    }

    /**
     * Configura las columnas de una tabla para que muestren los datos de la clase Comanda.
     *
     * @param tableView Tabla a configurar
     */
    private void configureTableColumns(TableView<Comanda> tableView) {
        // Columna para la cantidad
        TableColumn<Comanda, Integer> cantidadCol = new TableColumn<>("Cantidad");
        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        // Columna para el nombre del producto
        TableColumn<Comanda, String> nombreProductoCol = new TableColumn<>("Nombre Producto");
        nombreProductoCol.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));

        // Columna para el mensaje adicional
        TableColumn<Comanda, String> mensajeCol = new TableColumn<>("Mensaje");
        mensajeCol.setCellValueFactory(new PropertyValueFactory<>("mensaje"));

        // Agrega las columnas a la tabla
        tableView.getColumns().addAll(cantidadCol, nombreProductoCol, mensajeCol);
    }

    /**
     * Carga las órdenes desde la base de datos y las distribuye entre las tablas.
     *
     * @throws SQLException Si ocurre un error al consultar la base de datos
     */
    private void loadOrdenes() throws SQLException {
        // Obtiene las órdenes desde la base de datos
        ObservableList<Comanda> ordenes = cocinaDAO.datosOrden();

        // Número total de órdenes
        int totalOrdenes = ordenes.size();

        // Número de órdenes por tabla (distribución uniforme)
        int ordenesPorTableView = (int) Math.ceil(totalOrdenes / 4.0);

        // Listas para las órdenes de cada tabla
        ObservableList<Comanda> list1 = FXCollections.observableArrayList();
        ObservableList<Comanda> list2 = FXCollections.observableArrayList();
        ObservableList<Comanda> list3 = FXCollections.observableArrayList();
        ObservableList<Comanda> list4 = FXCollections.observableArrayList();

        // Divide las órdenes entre las tablas
        for (int i = 0; i < totalOrdenes; i++) {
            if (i < ordenesPorTableView) {
                list1.add(ordenes.get(i));
            } else if (i < 2 * ordenesPorTableView) {
                list2.add(ordenes.get(i));
            } else if (i < 3 * ordenesPorTableView) {
                list3.add(ordenes.get(i));
            } else {
                list4.add(ordenes.get(i));
            }
        }

        // Asigna las listas a las tablas correspondientes
        TableOrden01.setItems(list1);
        TableOrden02.setItems(list2);
        TableOrden03.setItems(list3);
        TableOrden04.setItems(list4);
    }

    /**
     * Finaliza una orden seleccionada de una tabla.
     *
     * @param tableView Tabla que contiene la orden a finalizar
     */
    private void finalizarOrden(TableView<Comanda> tableView) {
        // Obtiene la orden seleccionada
        Comanda selectedOrden = tableView.getSelectionModel().getSelectedItem();

        if (selectedOrden != null) {
            // Obtiene el ID de la orden
            int idOrden = 1; // Asegúrate de que Comanda tenga un método getId()

            try {
                // Marca la orden como finalizada en la base de datos
                cocinaDAO.finalizarOrden(idOrden);

                // Elimina la orden de la tabla
                tableView.getItems().remove(selectedOrden);
            } catch (SQLException e) {
                e.printStackTrace();
                // Aquí puedes mostrar un mensaje de error al usuario con un Alert
            }
        }
    }


    @FXML
//    void but_login(MouseEvent event) {
//
//    }

    public void but_login(javafx.scene.input.MouseEvent mouseEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/login-mesero.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) BtnFinalizar01.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

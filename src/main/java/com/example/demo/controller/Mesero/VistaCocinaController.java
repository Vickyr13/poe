package com.example.demo.controller.Mesero;

import com.example.demo.database.CocinaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Map;

public class VistaCocinaController {
    @FXML
    private Button BtnFinalizar01;

    @FXML
    private Button BtnFinalizar02;

    @FXML
    private Button BtnFinalizar03;

    @FXML
    private Button BtnFinalizar04;

    @FXML
    private Label NumMesa01;

    @FXML
    private Label NumMesa02;

    @FXML
    private Label NumMesa03;

    @FXML
    private Label NumMesa04;

    @FXML
    private TableView<Map<String, Object>> TableOrden01;
    @FXML
    private TableView<Map<String, Object>> TableOrden02;
    @FXML
    private TableView<Map<String, Object>> TableOrden03;
    @FXML
    private TableView<Map<String, Object>> TableOrden04;


    private CocinaDAO CocinaDAO = new CocinaDAO();

    @FXML
    private void initialize() {
        configureTableColumns(TableOrden01);
        configureTableColumns(TableOrden02);
        configureTableColumns(TableOrden03);
        configureTableColumns(TableOrden04);

        try {
            loadOrdenes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BtnFinalizar01.setOnAction(e -> finalizarOrden(TableOrden01));
        BtnFinalizar02.setOnAction(e -> finalizarOrden(TableOrden02));
        BtnFinalizar03.setOnAction(e -> finalizarOrden(TableOrden03));
        BtnFinalizar04.setOnAction(e -> finalizarOrden(TableOrden04));
    }

    private void configureTableColumns(TableView<Map<String, Object>> tableView) {
        TableColumn<Map<String, Object>, String> nombreProductoCol = new TableColumn<>("Nombre Producto");
        nombreProductoCol.setCellValueFactory(new PropertyValueFactory<>("nombre_producto"));

        TableColumn<Map<String, Object>, String> idOrdenCol = new TableColumn<>("ID Orden");
        idOrdenCol.setCellValueFactory(new PropertyValueFactory<>("id_orden"));

        TableColumn<Map<String, Object>, String> fechaOrdenCol = new TableColumn<>("Fecha Orden");
        fechaOrdenCol.setCellValueFactory(new PropertyValueFactory<>("fecha_orden"));

        TableColumn<Map<String, Object>, Double> numeroMesaCol = new TableColumn<>("Numero Mesa");
        numeroMesaCol.setCellValueFactory(new PropertyValueFactory<>("numero_mesa"));

        TableColumn<Map<String, Object>, String> subTotalCol = new TableColumn<>("Sub Total");
        subTotalCol.setCellValueFactory(new PropertyValueFactory<>("sub_total"));

        tableView.getColumns().addAll(nombreProductoCol, idOrdenCol, fechaOrdenCol, numeroMesaCol, subTotalCol);
    }


    private void loadOrdenes() throws SQLException {
        ObservableList<Map> ordenes = CocinaDAO.getOrdenes();

        int totalOrdenes = ordenes.size();
        int ordenesPorTableView = totalOrdenes / 4;

        ObservableList<Map<String, Object>> list1 = FXCollections.observableArrayList();
        ObservableList<Map<String, Object>> list2 = FXCollections.observableArrayList();
        ObservableList<Map<String, Object>> list3 = FXCollections.observableArrayList();
        ObservableList<Map<String, Object>> list4 = FXCollections.observableArrayList();

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

        TableOrden01.setItems(list1);
        TableOrden02.setItems(list2);
        TableOrden03.setItems(list3);
        TableOrden04.setItems(list4);
    }

    private void finalizarOrden(TableView<Map<String, Object>> tableView) {
        Map<String, Object> selectedOrden = tableView.getSelectionModel().getSelectedItem();
        if (selectedOrden != null) {
            int idOrden = (int) selectedOrden.get("id_orden");
            try {
                CocinaDAO.finalizarOrden(idOrden);
                tableView.getItems().remove(selectedOrden);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

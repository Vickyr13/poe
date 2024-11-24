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


}

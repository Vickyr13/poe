
package com.example.demo.controller.Mesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class VentanaEmegerteCocinaController {
    public Spinner candidaPlato;
    public Label nombrePlato;
    public Button but_continuar;
    @FXML
    private ComboBox<String> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private ComboBox<String> combo3;
    @FXML
    private ComboBox<String> combo4;
    @FXML
    private ComboBox<String> combo5;
    @FXML
    private Button but_cancelar;

    @FXML
    private void initialize() {
        //se agregan los items
//        combo1.getItems().addAll("items1", "items2", "items3");
//        combo2.getItems().addAll("items1", "items2", "items3");
//        combo3.getItems().addAll("items1", "items2", "items3");
//        combo4.getItems().addAll("items1", "items2", "items3");
//        combo5.getItems().addAll("items1", "items2", "items3");

    SpinnerValueFactory<Integer> valor = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10,1);
    candidaPlato.setValueFactory(valor);

    }



    public void but_cancelar(ActionEvent actionEvent) {

        // Cierra la ventana actual (emergente)
        Stage stage = (Stage) but_cancelar.getScene().getWindow();
        stage.close();

    }
}

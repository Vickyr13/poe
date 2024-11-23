package com.example.demo.controller.Mesero;

import com.example.demo.Model.Cliente;
import com.example.demo.database.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class VentanaDomicilioController {

    @FXML
    private TextField tex_Nombre;

    @FXML
    private TextField tex_Telefono;

    @FXML
    private TextField tex_apellido;

    public Button but_confirmar;
    @FXML
    private TextArea tex_direccion;
    @FXML
    private Button atras;

    private Stage mainStage;
    private int id_empleado;

    public void initialize(int id_empleado){
        this.id_empleado = id_empleado;

    }



    // Método para establecer el Stage principal
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    public void but_confirmar(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/vista-pedido.fxml"));
        Parent root = loader.load();

        String nombre = tex_Nombre.getText();
        String direccion = tex_direccion.getText();
        String telefono = tex_Telefono.getText();
        String apellido = tex_apellido.getText();

        Cliente cliente = new Cliente(direccion, telefono, nombre, apellido);
        ClienteDAO clientedao = new ClienteDAO();
         clientedao.ingresarCliente(cliente);

        vistapedidoController orden = loader.getController();
        orden.initialize("21" ,id_empleado);

        Stage currentStage = (Stage) but_confirmar.getScene().getWindow();
        currentStage.close();

        if (mainStage != null) {
            mainStage.close();
        }

        // Abrir la nueva vista
        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }

    public void but_atras(ActionEvent actionEvent) {
        Stage stage = (Stage) atras.getScene().getWindow();
        stage.close();
    }
}
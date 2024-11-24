package com.example.demo.controller.Mesero;

import com.example.demo.Model.Cliente;
import com.example.demo.Model.client;
import com.example.demo.Model.productos;
import com.example.demo.database.ClienteDAO;
import com.example.demo.database.OrdenesDAO;
import com.example.demo.database.productosDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BuscarClienteController {

    @FXML
    private Button atras;

    @FXML
    private Button bt_buscar;

    @FXML
    private Button bt_crearpedido;

    @FXML
    private TableColumn<client, String> column_cliente;

    @FXML
    private Label lb_registrar;

    @FXML
    private TableView<client> tb_cliente;

    @FXML
    private TextField tex_Nombre;

    @FXML
    public void initialize() throws SQLException {
        llenarTabla();

        lb_registrar.setOnMouseClicked(event -> {
            // Llamamos al método para abrir la nueva ventana y cerrar la actual
            abrirNuevaVentanaYCerrar();
        });
    }
    @FXML
    void bt_buscar(ActionEvent event) throws SQLException {
    String nombre = tex_Nombre.getText();;

  List<client> cliente = ClienteDAO.clienteFiltrado(nombre);

       tb_cliente.getItems().clear();;
       tb_cliente.getItems().addAll(cliente);
    setTabla();
}

public void setTabla(){
    column_cliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
}
    // llenar tabla de producto
    public void llenarTabla() throws SQLException {
        List<client> client = ClienteDAO.SelectClientes();
        tb_cliente.getItems().addAll(client);

        column_cliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
    }
    @FXML
    void bt_crearpedido(ActionEvent event) {
        // Verificar si hay un registro seleccionado en la TableView
        client clienteSeleccionado = tb_cliente.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado == null) {
            // Si no hay selección, mostrar un mensaje de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selección requerida");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un cliente para crear un pedido.");
            alert.showAndWait();
        } else {
            // Si hay un cliente seleccionado, abre la nueva ventana
            try {
                // Cargar la nueva ventana (por ejemplo, una ventana para crear un pedido)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/vista-pedido.fxml"));
                Parent root = loader.load();

                // Si necesitas pasar el cliente a la nueva ventana, puedes hacerlo a través del controlador
                vistapedidoController nuevaVentanaController = loader.getController();
                nuevaVentanaController.setCliente(clienteSeleccionado, 1, 20); // Método en la nueva ventana para recibir el cliente

                // Crear un nuevo Stage (ventana)
                Stage stage = new Stage();
                stage.setTitle("Crear Pedido");
                stage.setScene(new Scene(root));
                stage.show();


                // Cerrar la ventana actual
                Stage currentStage = (Stage) tb_cliente.getScene().getWindow();  // Obtener la ventana actual (stage)
                currentStage.close();
                // Opcional: Cerrar la ventana actual si es necesario
                // ((Stage) tb_cliente.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
                // Si hay un error al cargar la nueva ventana
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Hubo un error al abrir la ventana de creación de pedido.");
                alert.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void but_atras(ActionEvent event) {

    }

    private void abrirNuevaVentanaYCerrar() {
        try {
            // Cargar la nueva ventana (reemplaza "/path/to/ventanaDestino.fxml" con la ruta real de tu FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/ventanaDomicilio.fxml"));
            Parent root = loader.load();

            // Crear una nueva ventana
            Stage newStage = new Stage();
            newStage.setTitle("Nueva Ventana");  // Título de la nueva ventana
            newStage.setScene(new Scene(root));  // Configuramos la escena con el contenido cargado
            newStage.show();  // Mostramos la nueva ventana

            // Obtener el Stage actual (ventana actual)
            Stage currentStage = (Stage) lb_registrar.getScene().getWindow();
            currentStage.close();  // Cerrar la ventana actual

        } catch (IOException e) {
            e.printStackTrace();
            // Si hay un error al cargar la nueva ventana
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Hubo un error al abrir la nueva ventana.");
            alert.showAndWait();
        }

    //---------------------------------------------------------------------------------------------------

    }
}

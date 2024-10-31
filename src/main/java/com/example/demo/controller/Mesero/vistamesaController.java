package com.example.demo.controller.Mesero;

import com.example.demo.Model.Empleado;
import com.example.demo.database.EmpleadoDAO;
import com.example.demo.database.OrdenesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import javax.swing.*;


public class vistamesaController {
    public Label labelNombre;
    @FXML
    private Pane Pmesa5;

    @FXML
    private Button pagar;

    @FXML
    private  Pane panel;



    @FXML
    private TableColumn<Map, Object> columm_cantidad;

    @FXML
    private TableColumn<Map, Object> columm_productoPedido;

    @FXML
    private TableColumn<Map, Object> columm_comentario;

    @FXML
    private TableView<Map> table_pedidos;

    private  String rutaPedido = "/com/example/demo/views/Mesero/vista-pedido.fxml";
    private String rutaDomicilio= "/com/example/demo/views/Mesero/ventanaDomicilio.fxml";
    private String rutaPentiende= "/com/example/demo/views/Mesero/ventanaPedido.fxml";

    private int numeeroMesa = 0;

    public int pin = 1234;
    private int id_empleado = 0;

    @FXML
    public void initialize() throws SQLException {
        List<Empleado> empleadosLis = EmpleadoDAO.getDatosEnpleados(pin, 1);


        if (!empleadosLis.isEmpty()) {
            Empleado empleado = empleadosLis.get(0);

            String nameEmpleado = empleado.getNombre_Empleado();
            String apellidoEmpleado = empleado.getApellido_Empleado();
            id_empleado = empleado.getId_Empleado();

            labelNombre.setText(nameEmpleado + " " + apellidoEmpleado);

        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron empleados con ese pin.");
        }
    }


    public void llenarTable_Odenes(int nunMesa) throws SQLException {
        ObservableList<Map> lista = OrdenesDAO.datosOrden(nunMesa);
        table_pedidos.setItems(lista);

        columm_cantidad.setCellValueFactory(new MapValueFactory<>("cantidad"));
        columm_productoPedido.setCellValueFactory(new MapValueFactory<>("nombre_producto"));
        columm_comentario.setCellValueFactory(new MapValueFactory<>("mesaje"));

    }





    @FXML
    public void but_pagar() throws SQLException {

    }

    public void mesa1(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 1;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(1);
        }
    }

    public void mesa2(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 2;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(2);
        }
    }

    public void mesa3(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 3;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(3);
        }
    }

    public void mesa4(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 4;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(4);
        }
    }

    public void mesa5(MouseEvent mouseEvent) throws SQLException {
        if (mouseEvent.getClickCount() == 2) {
            numeeroMesa = 5;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(5);
        }
    }

    public void mesa6(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 6;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(6);
        }
    }

    public void mesa7(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 7;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(7);
        }
    }

    public void mesa8(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 8;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(8);
        }
    }

    public void mesa9(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 9;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(9);
        }
    }

    public void mesa10(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 10;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(10);
        }
    }

    public void mesa11(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 11;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(11);
        }
    }

    public void mesa12(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 12;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(12);
        }
    }

    public void mesa13(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 13;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(13);
        }
    }

    public void mesa14(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 14;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(14);
        }
    }

    public void mesa15(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 15;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(15);
        }
    }

    public void mesa16(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 16;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(16);
        }
    }

    public void mesa17(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 17;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(17);
        }
    }

    public void mesa18(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 18;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(18);
        }
    }

    public void mesa19(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 19;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(19);
        }
    }

    public void mesa20(MouseEvent mouseEvent) throws SQLException {
        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = 20;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(20);
        }
    }


    // vistas emejertes
    public void but_LimpiarMesa(ActionEvent actionEvent) {
        navegarNo(rutaPentiende);
    }

    public void but_domicilio(ActionEvent actionEvent) {

        navegarNo(rutaDomicilio);
    }


    public void navegar(String ruta){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            // Obtener el controlador de la nueva vista
            vistapedidoController pedidoController = loader.getController();

            // Pasar el número de mesa al método initialize
            pedidoController.initialize(String.valueOf(numeeroMesa), id_empleado);

            // Cambiar la escena a la nueva vista
            Stage stage = (Stage) Pmesa5.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void navegarNo(String ruta){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();




            // Crear un nuevo Stage para la ventana emergente
            Stage newStage = new Stage();
            Scene scene = new Scene(root);

            newStage.setScene(scene);

            // Mostrar la nueva ventana encima de la actual
            newStage.initOwner(Pmesa5.getScene().getWindow()); // Establece el propietario como la ventana actual
            newStage.initModality(Modality.APPLICATION_MODAL); // Hace que la ventana sea modal (bloquea la interacción con la ventana principal)
            newStage.showAndWait(); // Espera a que la ventana se cierre antes de volver al control de la ventana principal

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


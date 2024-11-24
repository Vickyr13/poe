package com.example.demo.controller.Mesero;

import com.example.demo.Model.Empleado;
import com.example.demo.database.EmpleadoDAO;
import com.example.demo.database.OrdenesDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    public Button but_agregar;
    public Label lab_mesero;
    public Label lab_numeroMesa;

    public Label lab_dineroTotal;

    public Label label_Total;
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
    private String rutaDomicilio= "/com/example/demo/views/Mesero/buscarcliente.fxml";
    private String rutaPentiende= "/com/example/demo/views/Mesero/ventanaPedido.fxml";

    private int numeeroMesa = 0;

    public int pin = 1234;
    private static int id_empleado = 0;

    private String nameEmpleado;
    private String apellidoEmpleado;
    int idOrden;

    @FXML
    public void initialize() throws SQLException {
        List<Empleado> empleadosLis = EmpleadoDAO.getDatosEnpleados(pin, 1);

        if (!empleadosLis.isEmpty()) {
            Empleado empleado = empleadosLis.get(0);

            nameEmpleado = empleado.getNombre_Empleado();
            apellidoEmpleado = empleado.getApellido_Empleado();
            id_empleado = empleado.getId_Empleado();

        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron empleados con ese pin.");
        }

            label_Total.setText( "" + OrdenesDAO.totalPrecio(numeeroMesa));
    }


    public void llenarTable_Odenes(int nunMesa) throws SQLException {
        OrdenesDAO orden = new OrdenesDAO();
        table_pedidos.getItems().clear();

        List<Map<String, Object>> productos = orden.DatosOrden(nunMesa);

        if (!productos.isEmpty()) {
            table_pedidos.getItems().addAll(productos);
        }

        columm_cantidad.setCellValueFactory(new MapValueFactory<>("cantidad"));
        columm_productoPedido.setCellValueFactory(new MapValueFactory<>("nombre_producto"));
        columm_comentario.setCellValueFactory(new MapValueFactory<>("mesaje"));

        lab_mesero.setText(nameEmpleado + " " + apellidoEmpleado);
        label_Total.setText("" + OrdenesDAO.totalPrecio(nunMesa));

        // Listener para obtener el id de la orden seleccionada
        // Listener para obtener el id de la orden seleccionada
        table_pedidos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && newSelection.get("id_orden") != null) {
                idOrden = (Integer) newSelection.get("id_orden");
                System.out.println("ID de la orden seleccionada: " + idOrden);
            }
        });
    }



    private void abrirNuevaVentanaConIdOrden(Integer idOrden) {
        if (idOrden == 0) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona una orden antes de proceder.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/pago.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del nuevo FXML y pasarle el idOrden
            PagoController controlador = loader.getController();
            controlador.recibirIdOrden(idOrden);

            String total = label_Total.getText();
            controlador.recibirTotal(total);

            // Configurar y mostrar la nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void but_pagar(ActionEvent event) {
        // Verificar si se ha seleccionado una orden
        if (idOrden == 0) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona una orden antes de proceder.");
            return;
        }
        abrirNuevaVentanaConIdOrden(idOrden);

    }

    @FXML
    public void abrirInterfazB() {
        try {
            // Cargar el FXML de InterfazB
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/pago.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de InterfazB
            PagoController controladorB = loader.getController();

            String total = label_Total.getText();
            // Pasar la variable mensaje a InterfazB
            controladorB.recibirTotal((total));
            controladorB.recibirIdOrden(idOrden);
            System.out.println("pasando en vista mesa" +total);

            // Mostrar InterfazB
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mesa1(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 1);
    }
    public void mesa2(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 2);
    }
    public void mesa3(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 3);
    }
    public void mesa4(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 4);
    }
    public void mesa5(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 5);
    }
    public void mesa6(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 6);
    }
    public void mesa7(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 7);
    }
    public void mesa8(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 8);
    }
    public void mesa9(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 9);
    }
    public void mesa10(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 10);
    }
    public void mesa11(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 11);
    }
    public void mesa12(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 12);
    }
    public void mesa13(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 13);
    }
    public void mesa14(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 14);

    }
    public void mesa15(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 15);
    }
    public void mesa16(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 16);
    }
    public void mesa17(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 17);
    }
    public void mesa18(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 18);
    }
    public void mesa19(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 19);
    }
    public void mesa20(MouseEvent mouseEvent) throws SQLException {
        ClickController(mouseEvent, 20);
    }


    // solo son metodos para pasar de vistas

    // vistas emejertes
    public void but_LimpiarMesa(ActionEvent actionEvent) throws IOException {
        // navegarNo(rutaPentiende);
        vistaVerOrdenes();
    }
    public void but_domicilio(ActionEvent actionEvent) throws IOException {
        try {
            // Cargar el FXML de la ruta especificada
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaDomicilio));
            Parent root = loader.load();

            // Crear una nueva escena con el contenido cargado
            Scene nuevaEscena = new Scene(root);

            // Obtener el Stage actual desde el evento
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Establecer la nueva escena en el Stage y mostrarla
            stage.setScene(nuevaEscena);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar la vista de domicilio.");
        }
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

    public void navegarNo(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            // Obtener el controlador de la nueva vista
            VentanaDomicilioController controller = loader.getController();
            controller.setMainStage((Stage) Pmesa5.getScene().getWindow());
            controller.initialize(id_empleado);

            Stage newStage = new Stage();
            Scene scene = new Scene(root);

            newStage.setScene(scene);
            newStage.initOwner(Pmesa5.getScene().getWindow());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void vistaVerOrdenes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/ventanaPedido.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.initOwner(Pmesa5.getScene().getWindow());
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();

    }

    public void but_agregar(ActionEvent actionEvent) throws IOException, SQLException {

        if(numeeroMesa == 0){
            JOptionPane.showMessageDialog(null, "Selecciones una mesa");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/vista-pedido.fxml"));
        Parent root = loader.load();

        vistapedidoController pedidoController = loader.getController();
        pedidoController.initialize(String.valueOf(numeeroMesa), id_empleado);

        // Cambiar la escena a la nueva vista
        Stage stage = (Stage) Pmesa5.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void but_pedidoMesa(ActionEvent actionEvent) {
    }

    public void ClickController(MouseEvent mouseEvent, int numeroMesa) throws SQLException {

        if(mouseEvent.getClickCount() == 2) {
            numeeroMesa = numeroMesa;
            navegar(rutaPedido);
        } else {
            llenarTable_Odenes(numeroMesa);
            // precioTotal();
            numeeroMesa = numeroMesa;
        }
    }

    int id;
    public void recibirId(int idrecibido) {
        id = idrecibido;// Muestra el mensaje en el Label
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
}

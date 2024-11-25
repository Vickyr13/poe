package com.example.demo.controller.Mesero;

import com.example.demo.Model.*;
import com.example.demo.database.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.JOptionPane; // Si solo se usa JOptionPane, se importa directamente
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*; // Para listas, ArrayList y otras clases de utilidad de java.util

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
    public TableColumn<productos, String> colummCategoria;

    @FXML
    private TableView<productos> tableView;
//----------------------------------------------------------------

    @FXML
    private TableColumn<llenarTableOrdenes, String> columm_cantidad;

    @FXML
    private TableColumn<llenarTableOrdenes, String> columm_productoPedido;

    @FXML
    public TableColumn<llenarTableOrdenes, String> columm_Extra;

    @FXML
    private TableColumn<llenarTableOrdenes, String> columm_subTotal;

    @FXML
    private TableView<llenarTableOrdenes> table_pedidos;
    @FXML
    private Label lb_fecha;

    @FXML
    private Label lb_hora;

    @FXML
    private Label lb_nombre;
    @FXML
    private Label label_numeroMesa;

    private int nunMesa;
    private int idOrden = -1;
    private int id_producto;
    private int id_butoon;
    private double subTotal;

    private String mensaje = "";
    private String cantidad;
    private String product = "";
    private String subtotal;

    //----------------------------------------------------------------

    private int id_empleado;
    CategoriaDAO querysCategoria = new CategoriaDAO();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //-------------------------------------------------------------------------------------------------

    @FXML
    public void initialize(String numeromesa, int id_Empleado, String name) throws SQLException {
        obtenerHora();
        obtenerFecha();
        lb_nombre.setText(name);
        //-----------------------------------------------------------------
        id_empleado = id_Empleado;
        label_numeroMesa.setText("Mesa # " + numeromesa);

        nunMesa = Integer.parseInt(numeromesa);
      //  label_numeroMesa.setText(numeromesa);

        idOrden = OrdenesDAO.obtenerOrdenActivaPorMesa(nunMesa);

        llenarTabla();
        cargarCategorias();
        updateCantidadDinerp(0.0);
        llenarTabla();

    }




    public void updateCantidadDinerp(double dineroUnidad){
        for(llenarTableOrdenes dinero : table_pedidos.getItems()){
            double subDinero = dinero.getSubcantidad();
            dineroUnidad += subDinero;
        }
        double Total = Math.round(dineroUnidad * 100.0) / 100.0;
        label_precioTotal.setText(String.valueOf(Total));



    }





    // llenar tabla de producto
    public void llenarTabla() throws SQLException {
        List<productos> venta = productosDAO.productoBenta();
        tableView.getItems().addAll(venta);

        columm_producto.setCellValueFactory(new PropertyValueFactory<>("nombre_Producto"));
        //colummCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria_Producto"));
        columm_descripcion.setCellValueFactory(new PropertyValueFactory<>("descriccios_Producto"));
        columm_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
    }


    // Método para pasar pasar datos
    public void aptenerProductToOrder(String[] datos) {
        this.mensaje = mensaje = datos[0];
        this.cantidad = cantidad = String.valueOf(Integer.parseInt(datos[1]));
        this.product = product = datos[2];
        this.subtotal = subtotal = String.valueOf(Double.parseDouble(datos[3]));
    }


    public void cargarCategorias() {
        cboCategoria.getItems().clear();
        cboCategoria.getItems().addAll(querysCategoria.obtenerCategoriasFiltradas());
    }


    // envia los datos a la otra vista
    public void but_Pasarvista(ActionEvent actionEvent) throws SQLException {
        try {
            this.id_butoon = 0;

            productos productoSeleccionado = tableView.getSelectionModel().getSelectedItem();
            // validar la linea
            if (productoSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
                return;
            }

            // Cargar el FXML de la ventana emergente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/ventanaEmegerteCocina.fxml"));
            Parent root = loader.load();

            // se crea una orden  o no bueno si existe si no, no
            if (idOrden == -1) {
                OrdenesDAO ordenDAO = new OrdenesDAO();
                Ordenes nuevaOrden = new Ordenes(0.0, "Activo");
                idOrden = ordenDAO.insertOrden(nuevaOrden);
            }
            id_producto = productoSeleccionado.getId_productos();
            String name = productoSeleccionado.getNombre_Producto();
            double subTotal = productoSeleccionado.getPrecio_unitario();
            //manda el id producto
            VentanaEmegerteCocinaController ventnanaEmegerteCocina = loader.getController();
            ventnanaEmegerteCocina.setVistapedidoController(this);
            ventnanaEmegerteCocina.initialize(id_producto, name, subTotal, id_butoon);

            // Crear y mostrar la nueva ventana
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.initOwner(but_pasarvista.getScene().getWindow());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();

            double Cantidad = Double.parseDouble(cantidad);
            subTotal *= Cantidad;
            updateCantidadDinerp(subTotal);
            llenarTavlarOrder();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void but_menu(MouseEvent mouseEvent) {
        try {
            if(!table_pedidos.getItems().isEmpty()) {
                if (!confirmarAccion("¿Seguro que quiere salir?", "Salir de la orden")) {
                    return;
                }
            }

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

    public void cboCategoria(ActionEvent actionEvent) throws SQLException {
        String categoriaSeleccionada = (String) cboCategoria.getValue();
        List<productos> venta = productosDAO.productoFiltradoCategoria(categoriaSeleccionada);

        tableView.getItems().clear();
        ;
        tableView.getItems().addAll(venta);
        setTabla();
    }

    public void setTabla() {
        columm_producto.setCellValueFactory(new PropertyValueFactory<>("nombre_Producto"));
        columm_descripcion.setCellValueFactory(new PropertyValueFactory<>("descriccios_Producto"));
        columm_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
    }


    public void bt_buscar(ActionEvent actionEvent) throws SQLException {
        String producto = txt_buscar.getText();
        ;

        List<productos> venta = productosDAO.productoFiltradoPalabra(producto);

        tableView.getItems().clear();
        ;
        tableView.getItems().addAll(venta);
        setTabla();
    }



    // a qui se va mandar a cocina siuuuuuu
    public void but_cocina(ActionEvent actionEvent) throws SQLException {

        if (table_pedidos.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista");
            return;
        }
        if (!confirmarAccion("¿Seguro que quiere enciar a cosina?", "Enviar a Cocina")) {
            return;
        }

        for (llenarTableOrdenes orden : table_pedidos.getItems()) {

            String cantidad = orden.getCantidad();
            String producto = orden.getProducto();
            String mensaje = orden.getMensajeP();
            id_producto = orden.getIdProducto();

            double subTotal = orden.getSubcantidad();
            int cantidadPlato = Integer.parseInt(cantidad);

            Detalles_ordenes detalleOrden = new Detalles_ordenes(idOrden, id_producto, nunMesa, cantidadPlato, subTotal, id_empleado, mensaje);

            Detalle_ordenesDAO detallesOrdenes = new Detalle_ordenesDAO();
            detallesOrdenes.insertDetallesOrdenes(detalleOrden);

//            JOptionPane.showMessageDialog(null, "id_Orden: " + idOrden + " id producto: " + id_producto +
//                    " Numero mesa: " + nunMesa + " Cantidad plato: " + cantidadPlato + " id Empleado: " + id_empleado +
//                    " Mensaje: " + mensaje + " total: " + subTotal);
        }

        table_pedidos.getItems().clear();
        label_precioTotal.setText("0.00");
    }

    public boolean confirmarAccion(String mensaje, String titulo) {
        // Mostrar un cuadro de confirmación
        int opcion = JOptionPane.showConfirmDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        // Retornar true si el usuario seleccionó "Sí", false si seleccionó "No"
        return opcion == JOptionPane.YES_OPTION;
    }




    //llenar table ordenes
    public List<llenarTableOrdenes> llenarTavlarOrder() {
        List<llenarTableOrdenes> orders = new ArrayList<>();

        productos productoSeleccionado = tableView.getSelectionModel().getSelectedItem();
        id_producto = productoSeleccionado.getId_productos();


        double cantidadP = Double.parseDouble(cantidad);
        double Sub_cantidad = Double.parseDouble(subtotal);

        double precioToalUnitario = Math.round((Sub_cantidad * cantidadP) * 100.0) / 100.0;

        orders.add(new llenarTableOrdenes(cantidad, product, mensaje, precioToalUnitario, id_producto));

        columm_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columm_productoPedido.setCellValueFactory(new PropertyValueFactory<>("producto"));
        columm_Extra.setCellValueFactory(new PropertyValueFactory<>("mensajeP"));
        columm_subTotal.setCellValueFactory(new PropertyValueFactory<>("subcantidad"));

        table_pedidos.getItems().addAll(orders);

        return orders;
    }


    //metodo para editar las ordenes
    public void but_editar(ActionEvent actionEvent) throws IOException {
        this.id_butoon = 1;
        // Cargar la ventana emergente
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/ventanaEmegerteCocina.fxml"));
        Parent root = loader.load();

        // Obtener la fila seleccionada
        llenarTableOrdenes ordenSeleccionada = table_pedidos.getSelectionModel().getSelectedItem();
        if (ordenSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una orden" + id_butoon);
            return;
        }

        String PlatosTotal = (ordenSeleccionada.getCantidad());

        // Pasar datos al controlador de la ventana emergente
        VentanaEmegerteCocinaController controladorEmergente = loader.getController();
        controladorEmergente.actualizarTableOrdenes(ordenSeleccionada, PlatosTotal, mensaje, id_butoon, Double.parseDouble(subtotal));

        // Mostrar la ventana emergente
        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.initOwner(but_pasarvista.getScene().getWindow());
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();
        updateCantidadDinerp(subTotal);

        table_pedidos.refresh();
    }


    //inicializar hora
    public void obtenerHora() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Obtiene la hora actual y la formatea
            String horaActual = LocalTime.now().format(formatter);
            // Actualiza el texto del Label
            lb_hora.setText(horaActual);
        }));

        // Ejecuta el Timeline indefinidamente
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    //inicialiar fecha
    public void obtenerFecha() {
        // Configura un Timeline para actualizar el Label cada minuto
        Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(1), event -> {
            // Obtiene la fecha actual y la formatea
            String fechaActual = LocalDate.now().format(dateFormatter);
            // Actualiza el texto del Label
            lb_fecha.setText(fechaActual);
        }));

        // Ejecuta el Timeline indefinidamente
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Establece la fecha inicial al iniciar la aplicación
        lb_fecha.setText(LocalDate.now().format(dateFormatter));

    }

    @FXML
    public void but_eliminar(ActionEvent actionEvent) {
        llenarTableOrdenes ordenSeleccionada = table_pedidos.getSelectionModel().getSelectedItem();
        if (ordenSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Seleccione una orden");
            return;
        }

        if (!confirmarAccion("¿Seguro que quiere eliminar?", "Eliminar orden")) {
            return;
        }
        // Obtener y validar el precio actual del label
        double total;
        try {
            total = Double.parseDouble(label_precioTotal.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al interpretar el precio total.");
            return;
        }

        // Restar el subtotal del elemento seleccionado
        double totalModificado = total - ordenSeleccionada.getSubcantidad();
        totalModificado = Math.round(totalModificado * 100.0) / 100.0; // Redondear a 2 decimales
        label_precioTotal.setText(String.valueOf(totalModificado));

        // Eliminar la orden seleccionada de la lista
        table_pedidos.getItems().remove(ordenSeleccionada);
    }



    // clase para la tabla de ordenes
    public class llenarTableOrdenes {
        private SimpleStringProperty cantidad;
        private SimpleStringProperty producto;
        private SimpleStringProperty mensajeP;
        private SimpleDoubleProperty subcantidad;
        private SimpleIntegerProperty idProducto;

        public llenarTableOrdenes(String cantidad, String producto, String mensajeP, double subcantidad, int idProducto) {
            this.cantidad = new SimpleStringProperty(cantidad);
            this.producto = new SimpleStringProperty(producto);
            this.mensajeP = new SimpleStringProperty(mensajeP);
            this.subcantidad = new SimpleDoubleProperty(subcantidad);
            this.idProducto = new SimpleIntegerProperty(idProducto);
        }

        public String getCantidad() {
            return cantidad.get();
        }

        public void setCantidad(String cantidad) {
            this.cantidad.set(cantidad);
        }

        public String getProducto() {
            return producto.get();
        }

        public void setProducto(String producto) {
            this.producto.set(producto);
        }

        public String getMensajeP() {
            return mensajeP.get();
        }

        public void setMensajeP(String mensajeP) {
            this.mensajeP.set(mensajeP);
        }

        public double getSubcantidad() {
            return subcantidad.get();
        }

        public void setSubcantidad(double subcantidad) {
            this.subcantidad.set(subcantidad);
        }

        public int getIdProducto() {
            return idProducto.get();
        }

        public void setIdProducto(int idProducto) {
            this.idProducto.set(idProducto);
        }
    }

    //metodo que recibe el cliente
    // Método para recibir el cliente
    public void setCliente(client cliente, int id_empleado, int nunMesa, String name) throws SQLException {
        this.id_empleado = id_empleado;
        this.nunMesa = nunMesa;

        lb_nombre.setText(name);
        label_numeroMesa.setText("A domicilio");
        obtenerHora();
        obtenerFecha();
        //-----------------------------------------------------------------

        llenarTabla();
        cargarCategorias();
        updateCantidadDinerp(0.0);

    }

}
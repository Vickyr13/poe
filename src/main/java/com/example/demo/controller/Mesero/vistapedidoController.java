package com.example.demo.controller.Mesero;

import com.example.demo.Model.*;
import com.example.demo.database.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    public TableColumn <productos, String> colummCategoria;

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

    private String mensaje = "";
    private String cantidad = "";
    private String product = "";
    private String subtotal = "";

    //----------------------------------------------------------------

    private int id_empleado;
    CategoriaDAO querysCategoria = new CategoriaDAO();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @FXML
    public void initialize(String numeromesa, int id_Empleado) throws SQLException {
        obtenerHora();
        obtenerFecha();
        //-----------------------------------------------------------------
        id_empleado = id_Empleado;
        label_numeroMesa.setText("El numero de la mesa: " + numeromesa);

        nunMesa = Integer.parseInt(numeromesa);
        label_numeroMesa.setText(numeromesa);

        idOrden = OrdenesDAO.obtenerOrdenActivaPorMesa(nunMesa);

        llenarTabla();
        cargarCategorias();
        precioTotal(0.0);


    }
    //inicializar hora
    public void obtenerHora(){
        // Configura un Timeline para actualizar el Label cada segundo
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
    public void obtenerFecha(){
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

    // llenar tabla de producto
    public void llenarTabla() throws SQLException {
        List<productos> venta = productosDAO.productoBenta();
        tableView.getItems().addAll(venta);

        columm_producto.setCellValueFactory(new PropertyValueFactory<>("nombre_Producto"));
        //colummCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria_Producto"));
        columm_descripcion.setCellValueFactory(new PropertyValueFactory<>("descriccios_Producto"));
        columm_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
    }


    //llenar table ordenes
    public List<llenarTableOrdenes> llenarTavlarOrder() {
        List<llenarTableOrdenes> orders = new ArrayList<>();

        productos productoSeleccionado = tableView.getSelectionModel().getSelectedItem();
        id_producto = productoSeleccionado.getId_productos();


        double cantidadP = Double.parseDouble(cantidad);
        double Sub_cantidad = Double.parseDouble(subtotal);

        orders.add(new llenarTableOrdenes(cantidad, product, mensaje, (Sub_cantidad * cantidadP) , id_producto));

        columm_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columm_productoPedido.setCellValueFactory(new PropertyValueFactory<>("producto"));
        columm_Extra.setCellValueFactory(new PropertyValueFactory<>("mensajeP"));
        columm_subTotal.setCellValueFactory(new PropertyValueFactory<>("subcantidad"));

        table_pedidos.getItems().addAll(orders);

        return orders;
    }


    // Método para pasar pasar datos
    public void aptenerProductToOrder(String[] datos) {
        this.mensaje = mensaje = datos[0];
        this.cantidad = cantidad = String.valueOf(Integer.parseInt(datos[1]));
        this.product = product = datos[2];
        this.subtotal = subtotal = String.valueOf(Double.parseDouble(datos[3]));
    }

    //metodo para ver el precio total
    private void precioTotal(double total) throws SQLException {

        for (llenarTableOrdenes orden : table_pedidos.getItems()) {
            double subTotal = orden.getSubcantidad();
            total += subTotal;
        }
        label_precioTotal.setText("Precio Total: $" + total);
    }


    public void cargarCategorias() {
        cboCategoria.getItems().clear();
        cboCategoria.getItems().addAll(querysCategoria.obtenerCategoriasFiltradas());
    }


    // envia los datos a la otra vista
    public void but_Pasarvista(ActionEvent actionEvent) throws SQLException {
        try {
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
            ventnanaEmegerteCocina.initialize(id_producto, name, subTotal);

            // Crear y mostrar la nueva ventana
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.initOwner(but_pasarvista.getScene().getWindow());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();

            double Cantidad = Double.parseDouble(cantidad);
            subTotal *= Cantidad;
            precioTotal(subTotal);
            llenarTavlarOrder();

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

    public void cboCategoria(ActionEvent actionEvent) throws SQLException {
        String categoriaSeleccionada = (String) cboCategoria.getValue();
        List<productos> venta = productosDAO.productoFiltradoCategoria(categoriaSeleccionada);

        tableView.getItems().clear();;
        tableView.getItems().addAll(venta);
        setTabla();
    }


    public void bt_buscar(ActionEvent actionEvent) throws SQLException {
        String producto = txt_buscar.getText();;

        List<productos> venta = productosDAO.productoFiltradoPalabra(producto);

        tableView.getItems().clear();;
        tableView.getItems().addAll(venta);
        setTabla();
    }

    public void setTabla(){
        columm_producto.setCellValueFactory(new PropertyValueFactory<>("nombre_Producto"));
        columm_descripcion.setCellValueFactory(new PropertyValueFactory<>("descriccios_Producto"));
        columm_precioUnitario.setCellValueFactory(new PropertyValueFactory<>("precio_unitario"));
    }

    // a qui se va mandar a cocina siuuuuuu
    public void but_cocina(ActionEvent actionEvent) throws SQLException {
        // Recorremos todos los elementos en la tabla
            for (llenarTableOrdenes orden : table_pedidos.getItems()) {

                String cantidad = orden.getCantidad();
                String producto = orden.getProducto();
                String mensaje = orden.getMensajeP();
                 id_producto = orden.getId_producto();

                double subTotal = orden.getSubcantidad();
                int cantidadPlato = Integer.parseInt(cantidad);

                Detalles_ordenes de = new Detalles_ordenes(idOrden, id_producto, nunMesa, cantidadPlato, subTotal, id_empleado, mensaje);

                Detalle_ordenesDAO detallesOrdenes = new Detalle_ordenesDAO();
                detallesOrdenes.insertDetallesOrdenes(de);

                JOptionPane.showMessageDialog(null, "id_Orden: " + idOrden + " id producto: " + id_producto +
                        " Numero mesa: " + nunMesa + " Cantidad plato: " + cantidadPlato + " id Empleado: " + id_empleado +
                        " Mensaje: " + mensaje + " total: " + subTotal);
            }

        table_pedidos.getItems().clear();
    }

    @FXML
    public void abrirInterfazB() {
        try {
            // Cargar el FXML de InterfazB
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/pago.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de InterfazB
            vistamesaController controladorB = loader.getController();

            int id = idOrden;
            // Pasar la variable mensaje a InterfazB
            controladorB.recibirId((id));
            System.out.println("pasando el id de la orden" +id);

            // Mostrar InterfazB
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //metodo para editar las ordenes
    public void but_editar(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/ventanaEmegerteCocina.fxml"));
        Parent root = loader.load();

        llenarTableOrdenes Taordeners = table_pedidos.getSelectionModel().getSelectedItem();
        if (Taordeners == null){
            JOptionPane.showMessageDialog(null, "Selecciones un ordenen");
            return ;
        }

        int cantida = Integer.parseInt(Taordeners.getCantidad());
        String mensaje = Taordeners.getMensajeP();


        VentanaEmegerteCocinaController ventanaEcocina = loader.getController();
        ventanaEcocina.actualizarTableOrdenes();


        Stage newStage = new Stage();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.initOwner(but_pasarvista.getScene().getWindow());
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();

    }


    // clase para la tabla de ordenes
    public class llenarTableOrdenes {
        private String cantidad;
        private String productos;
        private String mensajeP;  // El nombre del atributo es mensajeP
        private double subcantidad;
        private int id_producto;

        public llenarTableOrdenes(String cantidad, String productos, String mensajeP, double subcantidad, int id_producto) {
            this.cantidad = cantidad;
            this.productos = productos;
            this.mensajeP = mensajeP;
            this.subcantidad = subcantidad;
            this.id_producto = id_producto;
        }

        public String getCantidad() {
            return cantidad;
        }

        public void setCantidad(String cantidad) {
            this.cantidad = cantidad;
        }

        public String getProducto() {
            return productos;
        }

        public void setProducto(String producto) {
            this.productos = producto;
        }

        public String getMensajeP() {  // Cambia de getMesajeP() a getMensajeP()
            return mensajeP;
        }

        public void setMensajeP(String mensajeP) {
            this.mensajeP = mensajeP;
        }

        public double getSubcantidad() {
            return subcantidad;
        }

        public void setSubcantidad(double subcantidad) {
            this.subcantidad = subcantidad;
        }
        public int getId_producto() {
            return id_producto;
        }
        public void setId_producto(int id_producto) {
            this.id_producto = id_producto;
        }
    }


}
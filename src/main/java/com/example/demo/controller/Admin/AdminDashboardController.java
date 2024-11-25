package com.example.demo.controller.Admin;

import com.example.demo.database.DashboardDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class AdminDashboardController {

    public ImageView logo;
    @FXML
    private AnchorPane CategoriasPAnel;

    @FXML
    private AnchorPane DashboardPanel;

    @FXML
    private AnchorPane OrdenesPanel;

    @FXML
    private AnchorPane PanelORdenesAnulaes;

    @FXML
    private AnchorPane ProductosPanel;

    @FXML
    private AnchorPane UsuariosPanel;

    @FXML
    private ComboBox<Integer> cboAnos;

    @FXML
    private ComboBox<String> cboMeses;

    @FXML
    private AnchorPane panelIngresoAnual;

    @FXML
    private AnchorPane panelventas;

    @FXML
    private BarChart<?, ?> tablaBarras;

    @FXML
    private PieChart tablaDona1;

    @FXML
    private PieChart tabladona2;


    //Ordenes  totales
    @FXML
    private Label lblTotalOrdenes, lblTotalVentasAnual, lblTotalIngresoAnual, lblTotalIngresoMensual, lblTotalVentas, lblTotalIngresos; // Referencia al Label en el FXML

    @FXML
    private BarChart<String, Double> barChartVentas;
    @FXML
    private PieChart pieChartVentas;
    // Instancia de la clase DAO
    private DashboardDAO dashboardDAO;

    public void initialize() {
        //cboMeses.getItems().addAll("Enero", "Febrero", "Marzo", "Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre", "Noviembre", "Diciembre");
        //cboAnos.getItems().addAll(2024,2025,2026,2027);
        dashboardDAO = new DashboardDAO();
        cargarOrdenesTotales();
        mostrarTotalVentasAnual(); // Llama al método para mostrar las ventas
        mostrarTotalMontoVentasAnual();
        mostrarTotalIngresosUltimoMes();
        mostrarTotalVentas();
        mostrarMontoTotalVentasCompletadas();
        mostrarTopEmpleadosVentas();

        // Llenar el ComboBox con años (ejemplo: desde 2020 hasta el año actual)
        for (int i = 2020; i <= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i++) {
            cboAnos.getItems().add(i);
        }

        // Escuchar cambios en el ComboBox
        cboAnos.setOnAction(event -> mostrarVentasPorMes());
    }



    @FXML
    void ClickCategorias(MouseEvent event) {
        CambiarVista("AdminCategorias");
    }

    @FXML
    void ClickDashboard(MouseEvent event) {
        CambiarVista("AdminDashboard");
    }

    @FXML
    void ClickOrdenes(MouseEvent event) {
        CambiarVista("AdminOrdenes");
    }

    @FXML
    void ClickProductos(MouseEvent event) {
        CambiarVista("AdminProductos");
    }

    @FXML
    void ClickUsuarios(MouseEvent event) {
        CambiarVista("AdminUsuarios");
    }

    public void CambiarVista(String Direccion){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/"+Direccion+".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) DashboardPanel.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Metodo que permite mostrar las ordenes totales hechas que hace sido completadas
    private void cargarOrdenesTotales() {
        int totalOrdenes = dashboardDAO.obtenerTotalOrdenesCompletadas();
        lblTotalOrdenes.setText(String.valueOf(totalOrdenes));

    }

    // Método para mostrar el total de ventas completadas en el último año
    private void mostrarTotalVentasAnual() {
        int totalVentasAnual = dashboardDAO.obtenerTotalVentasAnual(); // Llama al método del DAO
        lblTotalVentasAnual.setText(String.valueOf(totalVentasAnual)); // Actualiza el Label
    }

    // Método para mostrar el monto total de ventas anuales
    private void mostrarTotalMontoVentasAnual() {
        double totalMontoVentas = dashboardDAO.obtenerTotalMontoVentasAnual();
        lblTotalIngresoAnual.setText(String.format("$ %.2f", totalMontoVentas)); // Actualiza Label
    }

    //Metodo para mostrar ingresos en el ultimo mes
    private void mostrarTotalIngresosUltimoMes(){
        double ingreso = dashboardDAO.obtenerIngresoUltimoMes();
        lblTotalIngresoMensual.setText(String.format("$%.2f", ingreso));
    }

    //Metodo de total de ordenes registradas en la aplicacion
    private void mostrarTotalVentas(){
        int totalOrdenes = dashboardDAO.obtenerTotalOrdenesHechas();
        lblTotalVentas.setText(String.valueOf(totalOrdenes));
    }

    //Metodo para mostrar el monto total de ventas completadas en la aplicacion
    private void mostrarMontoTotalVentasCompletadas(){
        double totalOrdenes = dashboardDAO.obtenerTotalOrdenesHechas();
        lblTotalIngresos.setText(String.format("$%.2f", totalOrdenes));
    }

    // Método para mostrar las ventas por mes en el BarChart
    private void mostrarVentasPorMes() {
        Integer añoSeleccionado = cboAnos.getValue();
        if (añoSeleccionado != null) {
            Map<String, Double> ventasPorMes = dashboardDAO.obtenerVentasPorMes(añoSeleccionado);

            // Limpiar el gráfico antes de agregar nuevos datos
            barChartVentas.getData().clear();

            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.setName("Ventas " + añoSeleccionado);

            // Agregar los datos al gráfico
            for (Map.Entry<String, Double> entry : ventasPorMes.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            // Añadir la serie al BarChart
            barChartVentas.getData().add(series);
        }
    }
    private void mostrarTopEmpleadosVentas() {
        ObservableList<PieChart.Data> datos = dashboardDAO.obtenerTopEmpleadosVentas();
        pieChartVentas.setData(datos);
    }

    public void but_login(MouseEvent mouseEvent) throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Mesero/login-mesero.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) logo.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            }

}






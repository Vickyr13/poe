    package com.example.demo.controller.Mesero;

    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.stage.Stage;

    import javax.swing.*;
    import java.io.IOException;
    import java.sql.SQLException;

    public class VentanaEmegerteCocinaController {

        public Spinner<Integer> candidaPlato;
        public Label nombrePlato;
        public Button but_cofirmar;
        public TextArea texMesaje;

        @FXML
        private Button but_cancelar;

        private int id_Producto;
        private String producto;
        private double sub_Total;
        private int id_butoon;

        @FXML
        public void initialize(int idProducto, String Producto, double subTotal, int id_butoon) {
            this.id_Producto = idProducto;
            this.producto = Producto;
            this.sub_Total = subTotal;
            this.id_butoon = id_butoon;

            SpinnerValueFactory<Integer> valor = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
            candidaPlato.setValueFactory(valor);
        }

        public void but_cancelar(ActionEvent actionEvent) {
            // Cierra la ventana actual (emergente)
            Stage stage = (Stage) but_cancelar.getScene().getWindow();
            stage.close();

        }

        public void but_cofirmar(ActionEvent actionEvent) throws SQLException, IOException {

            String[] datos = new String[4];
            datos[0] = texMesaje.getText();
            datos[1] = String.valueOf(candidaPlato.getValue());
            datos[2] = producto;
            datos[3] = String.valueOf(sub_Total);

            if (vistaPedido != null) {
                vistaPedido.aptenerProductToOrder(datos);


            } else {
                System.out.println("Error: Controlador 'vistapedidoController' no inicializado.");
            }

            // perte pra editar la orden
            if (id_butoon == 1) {

                double precioTotal = precioUnidad * candidaPlato.getValue();
                double redondeadoPrecio = Math.round(precioTotal * 100.0) / 100.0;
                ordenActual.setMensajeP(texMesaje.getText());
                ordenActual.setSubcantidad (redondeadoPrecio);
                ordenActual.setCantidad(String.valueOf(candidaPlato.getValue()));
                // Cerrar la ventana
                ((Stage) but_cofirmar.getScene().getWindow()).close();
                return;
            }



            Stage stage = (Stage) but_cofirmar.getScene().getWindow();
            stage.close();
        }

        private vistapedidoController vistaPedido;
        public void setVistapedidoController(vistapedidoController vista) {
            this.vistaPedido = vista;
        }


        private vistapedidoController.llenarTableOrdenes ordenActual;
        private double precioUnidad = 0.0;

        public void actualizarTableOrdenes(vistapedidoController.llenarTableOrdenes orden, String cantidad, String mesaje, int id_butoon, double precioUnidad) {
            this.ordenActual = orden;
            this.id_butoon = id_butoon;
            this.precioUnidad = precioUnidad;

            texMesaje.setText(mesaje);
            int canti = Integer.parseInt(cantidad);
            SpinnerValueFactory<Integer> valor = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, canti);
            candidaPlato.setValueFactory(valor);

            // Llenar los campos con los datos actuales de la orden
            texMesaje.setText(orden.getMensajeP());

        }
    }
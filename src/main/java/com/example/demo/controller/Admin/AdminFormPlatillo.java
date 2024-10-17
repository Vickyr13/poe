package com.example.demo.controller.Admin;

import com.example.demo.Model.productos;
import com.example.demo.database.CategoriaDAO;
import com.example.demo.database.conneection;
import com.example.demo.database.productosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AdminFormPlatillo {
    @FXML
    public RadioButton rdoInactivo;
    @FXML
    public ToggleGroup GroupEstado;
    @FXML
    public RadioButton rdoActivo;
    @FXML
    public Button btnVolver;
    @FXML
    public TextArea txaDescripcion;
    @FXML
    public TextField txtPrecio_producto;
    @FXML
    public Button Btn_Agregar_Plato;
    @FXML
    private TextField Txt_NombreP;
    @FXML
    private ComboBox<String> cboCategoria;

    CategoriaDAO querysCategoria = new CategoriaDAO();

    @FXML
    public void initialize() {
        cargarCategorias();
    }

    public void cargarCategorias() {
        cboCategoria.getItems().clear();
        cboCategoria.getItems().addAll(querysCategoria.obtenerCategorias());
    }


    private int obtenerIdCategoria(String categoria) {
        // Conexión a la base de datos
        Connection conn = conneection.getConnection();
        // Consulta SQL que valida la categoría
        String sql = "SELECT id_categoria FROM categorias WHERE nombre_categoria = ?";
        int idCategoria = -1;
        try {
            // Preparar la consulta
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria);

            // Ejecutar la consulta
            ResultSet resultSet = stmt.executeQuery();

            // Verificar si se encontraron resultados
            if (resultSet.next()) {
                // Extraer el valor de id_categoria
                idCategoria = resultSet.getInt("id_categoria");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al encontrar la categoría: " + e.getMessage(), e);
        }
        finally {
            // Cerrar la conexión, el PreparedStatement y el ResultSet
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Devolver el id de la categoría o -1 si no se encontró
        return idCategoria;
    }

    public void REGRESAR(ActionEvent actionEvent) {
        CambiarVista("AdminProductos");
    }

    public void CambiarVista(String Direccion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/views/Admin/" + Direccion + ".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int idBoton(int idboton) {
        return idboton;
    }

    public void Btn_Agregar_Plato(ActionEvent actionEvent) {
        productosDAO querys = new productosDAO();
        AdminProductoController ProductoController = new AdminProductoController();

        if (AdminProductoController.getId_button() == 1) {
            guardarCambios();
            return;
        }

        if (AdminProductoController.getId_button() == 2){
            if (validar()) {
                String nombre_Producto = Txt_NombreP.getText();
                double precio_unitario = Double.parseDouble(txtPrecio_producto.getText().trim());
                String descripcion_Producto = txaDescripcion.getText().trim();

                int estado = 0;
                if (rdoActivo.isSelected()) {
                    estado = 1;
                }
                if (rdoInactivo.isSelected()) {
                    estado = 0;
                }

                //Obtener el id de la categoría seleccionada
                int id_categoria = obtenerIdCategoria(cboCategoria.getValue());
                productos productos = new productos(id_categoria, nombre_Producto, precio_unitario, descripcion_Producto, estado);

                try {

                    querys.insertarProducto(productos);
                } catch (Exception e) {
                    System.out.println("Error al insertar productos " + e.getMessage());
                }

            }
            return;
        }
    }

    private boolean validar() {
        //Validar campos
        if (Txt_NombreP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingres el nombre del producto");
            return false;
        }

        if (cboCategoria.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione la categoria del producto");
            return false;
        }


        String precioTexto = txtPrecio_producto.getText().trim();

        if (precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el precio del producto");
            return false;
        }

        try {
            double precio_product = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio debe ser un número válido");
            return false;
        }

        if (!rdoActivo.isSelected() && !rdoInactivo.isSelected()) {
            JOptionPane.showMessageDialog(null, "Seleccione el estado del producto");
            return false;
        }

        if (txaDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingres la descripcion del producto");
            return false;
        }
        return true;
    }



    private int idProducto;  // Variable para almacenar el ID del producto que estamos editando

    public void cargarDatosParaEditar(Map<String, Object> producto, String idProducto2) {
        Txt_NombreP.setText((String) producto.get("nombre_producto"));
        txtPrecio_producto.setText(String.valueOf(producto.get("precio_unitario")));
        txaDescripcion.setText((String) producto.get("descripcion"));

        // Establecer el estado activo/inactivo
        String estado = (String) producto.get("estado_producto");
        if ("Activo".equals(estado)) {
            rdoActivo.setSelected(true);
        } else {
            rdoInactivo.setSelected(true);
        }

        // Establecer la categoría
        cboCategoria.setValue((String) producto.get("nombre_categoria"));

        // Guardar el id_producto
        try {
            idProducto = Integer.parseInt(idProducto2);  // Convertir el ID a entero
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el idProducto a entero: " + e.getMessage());
            this.idProducto = -1;
        }
    }


    // Método para guardar los cambios
    public void guardarCambios() {
        productosDAO querys = new productosDAO();
        if (validar()) {
            String nombre_Producto = Txt_NombreP.getText();
            double precio_unitario = Double.parseDouble(txtPrecio_producto.getText().trim());
            String descripcion_Producto = txaDescripcion.getText().trim();

            int estado = rdoActivo.isSelected() ? 1 : 0;

            int id_categoria = obtenerIdCategoria(cboCategoria.getValue());

            // Actualizar los datos en la base de datos usando idProducto
            try {
                querys.actualizarProducto(idProducto, id_categoria, nombre_Producto, precio_unitario, descripcion_Producto, estado);
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
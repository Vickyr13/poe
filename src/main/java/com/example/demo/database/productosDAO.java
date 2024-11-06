package com.example.demo.database;

import com.example.demo.Model.productos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class productosDAO {

    private static final String COLUMN_ID_PRODUCTOS = "id_producto";
    private static final String COLUMN_NOMBRE_CATEGORIA = "nombre_categoria"; // Cambiar el nombre aquí
    private static final String COLUMN_NOMBRE_PRODUCTO = "nombre_producto";
    private static final String COLUMN_PRECIO_UNITARIO = "precio_unitario";
    private static final String COLUMN_DESCRIPCION_PRODUCTO = "descripcion";
    private static final String COLUMN_ESTADO_PRODUCTO = "estado_producto";

    public productosDAO() {
    }

    public void insertarProducto(productos productos) throws SQLException {
        // establecer la conexion a la base de datos
        Connection con = conneection.getConnection();

        // Verificamos la conexion a la base de datos
        if (con != null && !con.isClosed()) {
            // ejecutar la query para insertar empleado
            try {
                // query de insertar producto
                String query = "INSERT INTO productos (id_categoria, nombre_producto, precio_unitario, descripcion, estado_producto) VALUES (?,?,?,?,?)";

                // preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);

                pstmt.setInt(1, productos.getId_categoria());
                pstmt.setString(2, productos.getNombre_Producto());
                pstmt.setDouble(3, productos.getPrecio_unitario());
                pstmt.setString(4, productos.getDescriccios_Producto());
                pstmt.setInt(5, productos.getEstado_Productos());

                // ejecutar la sentencia
                pstmt.execute();
                JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al insertar datos de producto: " + e.getMessage());
                throw e;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener productos de la base de datos
    public ObservableList<Map> getProductos() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "SELECT " +
                "id_producto, nombre_producto, c.nombre_categoria, precio_unitario, descripcion, estado_producto " +
                "FROM productos p " +
                "JOIN categorias c ON p.id_categoria = c.id_categoria;";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                producto.put("id_producto", rs.getString("id_producto"));
                producto.put("nombre_producto", rs.getString("nombre_producto"));
                producto.put("nombre_categoria", rs.getString("nombre_categoria"));
                producto.put("precio_unitario", rs.getDouble("precio_unitario"));
                producto.put("descripcion", rs.getString("descripcion"));

                // Cambiar la lógica del estado del producto
                String estadoTexto = (rs.getInt("estado_producto") == 1) ? "Activo" : "Inactivo";
                producto.put("estado_producto", estadoTexto);

                lista.add(producto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener productos: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    public void actualizarProducto(int idProducto, int idCategoria, String nombreProducto, double precioUnitario, String descripcion, int estadoProducto) throws SQLException {
        String query = "UPDATE productos SET id_categoria = ?, nombre_producto = ?, precio_unitario = ?, descripcion = ?, estado_producto = ? WHERE id_producto = ?";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idCategoria);
            ps.setString(2, nombreProducto);
            ps.setDouble(3, precioUnitario);
            ps.setString(4, descripcion);
            ps.setInt(5, estadoProducto);
            ps.setInt(6, idProducto);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el producto: " + e.getMessage(), e);
        }
    }





    //metodo para buscar un producto en la barra de busqueda en la vista de pedidos
    public void BuscarProductoFiltrado(String productos) throws SQLException {
        // establecer la conexion a la base de datos
        Connection con = conneection.getConnection();

        // Verificamos la conexion a la base de datos
        if (con != null && !con.isClosed()) {
            // ejecutar la query para insertar empleado
            try {
                // query de buscar producto
                String query = "select nombre_producto, descripcion, precio_unitario from productos where nombre_producto = '%"+productos+"%';";

                // preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);

                // ejecutar la sentencia
                pstmt.execute();
                JOptionPane.showMessageDialog(null, "Productos buscado correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al buscar datos de productos: " + e.getMessage());
                throw e;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }









    //mostar datos en la parte de meseros
    public static List<productos> productoBenta() throws SQLException {
        List<productos> productosList = new ArrayList<>();
        Connection con = conneection.getConnection();

        if (con!= null) {
            String sql = "select id_producto, nombre_producto, descripcion, precio_unitario from productos;";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre_producto");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio_unitario");

                productos aja = new productos(id_producto, nombre, descripcion, precio);
                productosList.add(aja);
            }
        }
        return productosList;
    }


    //filtro para buscar por palabra/nombre con el boton en la vista de meseros
    public static List<productos> productoFiltradoPalabra(String nombre_producto) throws SQLException {
        List<productos> productosList = new ArrayList<>();
        Connection con = conneection.getConnection();

        if (con != null) {
            // Cambia el operador '=' por 'LIKE' y usa ? para el parámetro
            String query = "SELECT id_producto, nombre_producto, descripcion, precio_unitario FROM productos WHERE nombre_producto LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + nombre_producto + "%");  // Usar '%' para buscar coincidencias parciales
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre_producto");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio_unitario");

                productos producto = new productos(id_producto, nombre, descripcion, precio);
                productosList.add(producto);
            }
        }
        return productosList;
    }

    public static List<productos> productoFiltradoCategoria(String categoria) throws SQLException {
        List<productos> productosList = new ArrayList<>();
        Connection con = conneection.getConnection();

        if (con != null) {
            String query = "SELECT p.id_producto, p.nombre_producto, p.descripcion, p.precio_unitario, c.nombre_categoria " +
                    "FROM productos p " +
                    "JOIN categorias c ON c.id_categoria = p.id_categoria " +
                    "WHERE c.nombre_categoria LIKE ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + categoria + "%");  // Usar '%' para buscar coincidencias parciales
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre_producto");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio_unitario");

                // Crear objeto 'producto' con los datos recuperados
                productos producto = new productos(id_producto, nombre, descripcion, precio);
                productosList.add(producto);
            }
        }
        return productosList;
    }

}
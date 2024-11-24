package com.example.demo.database;

import com.example.demo.Model.Ordenes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CocinaDAO {
    // consulta para la tabla de admin
    public ObservableList<Map> getOrdenes() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select p.nombre_producto, od.id_orden, o.fecha_orden, m.numero_mesa, od.sub_total\n" +
                "from detalle_ordenes od\n" +
                "join productos p On od.id_producto = p.id_producto\n" +
                "join ordenes o On od.id_orden = o.id_orden\n" +
                "join mesa m on od.id_mesa = m.id_mesa;"+
                "WHERE on.estado_mesa = 'activo'";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                producto.put("nombre_producto", rs.getString("nombre_producto"));
                producto.put("id_orden", rs.getString("id_orden"));
                producto.put("fecha_orden", rs.getString("fecha_orden"));
                producto.put("numero_mesa", rs.getDouble("numero_mesa"));
                producto.put("sub_total", rs.getString("sub_total"));

                lista.add(producto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
            throw e;
        }

        return lista;
    }


    // Método para finalizar una ordenes
    public void finalizarOrden(int idOrden) throws SQLException {
        String sql = "UPDATE ordenes SET estado = 'inactiva' WHERE id_orden = ?";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al finalizar la orden: " + e.getMessage());
            throw e;
        }
    }


    public int insertOrden(Ordenes ordenes) {
        String sql = "INSERT INTO ordenes (precio_total, estado) VALUES (?,?)";
        Connection con = conneection.getConnection();
        int generatedId = -1;  // Variable para almacenar el ID generado

        try {
            // Aquí indicamos que queremos devolver las claves generadas (en este caso, el ID)
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, ordenes.getprecio_orden());
            pstmt.setString(2, ordenes.getEstado_orden());

            // Ejecutar la sentencia de inserción
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {

                // Obtener el ID generado
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
                rs.close();
            } else {
                JOptionPane.showMessageDialog(null, "No se insertó la orden");
            }

            // Cerrar el PreparedStatement después de su uso
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar la orden: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return generatedId;
    }


    //CONSULTA ESPECIAL
    public static ObservableList<Map> datosOrden(int numeroMesa) {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "SELECT \n" +
                "    o.id_orden,\n" +
                "    cantidad,\n" +
                "    p.nombre_producto, \n" +
                "    mesaje\n" +
                "FROM detalle_ordenes od\n" +
                "JOIN mesa m ON od.id_mesa = m.id_mesa\n" +
                "JOIN ordenes o ON od.id_orden = o.id_orden\n" +
                "JOIN productos p ON od.id_producto = p.id_producto\n" +
                "JOIN categorias c ON p.id_categoria = c.id_categoria\n" +
                "WHERE m.numero_mesa = ?;";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Establecer el valor del número de mesa en el PreparedStatement
            ps.setInt(1, numeroMesa);

            // Ahora ejecutas la consulta
            try (ResultSet rs = ps.executeQuery()) {

                // Procesar los resultados
                while (rs.next()) {
                    Map<String, Object> producto = new HashMap<>();
                    producto.put("id_orden", rs.getInt("id_orden"));
                    producto.put("cantidad", rs.getInt("cantidad"));
                    producto.put("nombre_producto", rs.getString("nombre_producto"));
                    producto.put("mesaje", rs.getString("mesaje"));

                    lista.add(producto);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error obtener los datos de la orden" + e.getMessage());
            throw new RuntimeException(e);
        }
        return lista;
    }


    // odtener el precio total
    public static double totalPrecio(int numero_mesa) {
        double total = 0;
        String sql = "select SUM(sub_total) AS total_sub_total\n" +
                "                FROM detalle_ordenes od\n" +
                "                join mesa m On od.id_mesa = m.id_mesa\n" +
                "                where m.numero_mesa = ?;";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numero_mesa);

            // Ahora ejecutas la consulta
            try (ResultSet rs = ps.executeQuery()) {
                // Procesar los resultados
                while (rs.next()) {
                    total = rs.getDouble("total_sub_total");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error obtener el total" + e.getMessage());
            throw new RuntimeException(e);
        }
        return total;
    }



    public static int obtenerOrdenActivaPorMesa(int numeroMesa) {
        String sql = "select o.id_orden \n" +
                "from detalle_ordenes od \n" +
                "join ordenes o On od.id_orden = o.id_orden\n" +
                "join mesa m On od.id_mesa = m.id_mesa\n" +
                "where m.id_mesa = ? and o.estado = \"activo\";";
        Connection con = conneection.getConnection();
        int idOrden = -1;
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, numeroMesa);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idOrden = rs.getInt("id_orden");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la orden activa: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return idOrden;
    }
}

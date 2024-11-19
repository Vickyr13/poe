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

    // Método para obtener las órdenes pendientes en la vista de cocina
    public static ObservableList<Map> datosOrden(int numeroMesa) {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "SELECT \n" +
                "    cantidad,\n" +
                "    p.nombre_producto, \n" +
                "    mesaje\n" +
                "FROM detalle_ordenes od\n" +
                "JOIN mesa m ON od.id_mesa = m.id_mesa\n" +
                "JOIN ordenes o ON od.id_orden = o.id_orden\n" +
                "JOIN productos p ON od.id_producto = p.id_producto\n" +
                "WHERE m.numero_mesa = ?;";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numeroMesa);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> producto = new HashMap<>();
                    producto.put("cantidad", rs.getInt("cantidad"));
                    producto.put("nombre_producto", rs.getString("nombre_producto"));
                    producto.put("mesaje", rs.getString("mesaje"));
                    lista.add(producto);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los datos de la orden: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return lista;
    }

    // Método para finalizar una orden
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

}

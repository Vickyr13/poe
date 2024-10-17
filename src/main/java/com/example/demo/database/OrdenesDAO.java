package com.example.demo.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrdenesDAO {

    public ObservableList<Map> getOrdenes() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select p.nombre_producto, od.id_orden, o.fecha_orden, m.numero_mesa, od.sub_total\n" +
                "from detalle_ordenes od\n" +
                "join productos p On od.id_producto = p.id_producto\n" +
                "join ordenes o On od.id_orden = o.id_orden\n" +
                "join mesa m on od.id_mesa = m.id_mesa;";

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
            JOptionPane.showMessageDialog(null,"Error al obtener productos: " + e.getMessage());
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

}

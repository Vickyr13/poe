package com.example.demo.database;

import com.example.demo.Model.Comanda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CocinaDAO {

    // Método para obtener las órdenes pendientes en la vista de cocina
    public static ObservableList<Comanda> datosOrden() {
        ObservableList<Comanda> lista = FXCollections.observableArrayList();

        String sql = "SELECT " +
                "    cantidad, " +
                "    p.nombre_producto, " +
                "    mesaje " +
                "FROM detalle_ordenes od " +
                "JOIN mesa m ON od.id_mesa = m.id_mesa " +
                "JOIN ordenes o ON od.id_orden = o.id_orden " +
                "JOIN productos p ON od.id_producto = p.id_producto " +
                "WHERE estado = 'Activo';";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

       ///     ps.setInt(1, 2);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int cantidad = rs.getInt("cantidad");
                    String nombreProducto = rs.getString("nombre_producto");
                    String mensaje = rs.getString("mesaje");

                    Comanda comanda = new Comanda(cantidad, nombreProducto, mensaje);
                    lista.add(comanda);
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

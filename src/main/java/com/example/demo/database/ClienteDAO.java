package com.example.demo.database;

import com.example.demo.Model.Cliente;
import com.example.demo.Model.client;
import com.example.demo.Model.productos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.database.conneection.getConnection;

public class ClienteDAO {


    public void ingresarCliente(Cliente cliente) {
        Connection con = getConnection();
        if(con  == null){
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
            return;
        }

        try {
            String sql = "INSERT INTO clientes (direccion, telefono, nombre_cliente, apellido_cliente) VALUES (?,?,?,?); ";
            java.sql.PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, cliente.getDireccion_Cliente());
            pstmt.setString(2, cliente.getTelefono_Cliente());
            pstmt.setString(3, cliente.getNombre_Cliente());
            pstmt.setString(4, cliente.getApellido_Cliente());
            pstmt.execute();

            JOptionPane.showMessageDialog(null, "Cliente insertado corecta mente");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos del cliente: " + ex.getMessage());
            throw new RuntimeException(ex);
        }


    }
    public static List<client> SelectClientes() throws SQLException {
        // Lista para almacenar los nombres de los clientes
        List<client> nombresList = new ArrayList<>();

        // Establecer conexión
        Connection con = conneection.getConnection();

        // Verificar si la conexión es válida
        if (con != null) {
            // Consulta para seleccionar solo el nombre de los clientes
            String sql = "SELECT nombre_cliente FROM clientes;";

            // Preparar la consulta
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                // Obtener el nombre del cliente y agregarlo a la lista
                String nombre = rs.getString("nombre_cliente");
                nombresList.add(new client(nombre));

            }

            // Cerrar el ResultSet y PreparedStatement
            rs.close();
            ps.close();
        }

        // Retornar la lista de nombres
        return nombresList;
    }

    public static List<client> clienteFiltrado(String nombre) throws SQLException {
        List<client> clientesList = new ArrayList<>();
        Connection con = conneection.getConnection();

        if (con != null) {
            String query = "SELECT nombre_cliente FROM clientes WHERE nombre_cliente LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + nombre + "%");  // Usar '%' para buscar coincidencias parciales
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreCliente = rs.getString("nombre_cliente");
                clientesList.add(new client(nombreCliente));
            }
        }
        return clientesList;
    }



}

package com.example.demo.database;

import com.example.demo.Model.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

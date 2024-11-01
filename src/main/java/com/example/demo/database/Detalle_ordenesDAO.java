package com.example.demo.database;

import com.example.demo.Model.Detalles_ordenes;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Detalle_ordenesDAO {

    // metodo para insertar telefono
    public void insertDetallesOrdenes(Detalles_ordenes detallesOrdenes) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "INSERT INTO detalle_ordenes (id_orden, id_producto, id_mesa, cantidad, sub_total, id_empleado, mesaje) values(?,?,?,?,?,?, ?);";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, detallesOrdenes.getId_orden());
                pstmt.setInt(2, detallesOrdenes.getId_Producto());
                pstmt.setInt(3, detallesOrdenes.getId_mesa());
                pstmt.setInt(4, detallesOrdenes.getCantidad());
                pstmt.setDouble(5, detallesOrdenes.getSub_cantidad());
                pstmt.setInt(6, detallesOrdenes.getId_empleados());
                pstmt.setString(7, detallesOrdenes.getMesaje());


                //ejecutar la sentencia
                 pstmt.execute();
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al insertar pedido: " + e.getMessage());
                throw e;
            }
        }
    }
}


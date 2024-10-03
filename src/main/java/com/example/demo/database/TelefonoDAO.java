package com.example.demo.database;

import com.example.demo.Model.Empleado;
import com.example.demo.Model.Telefono;

import java.sql.Connection;
import java.sql.SQLException;

public class TelefonoDAO {
    public void insertTelefono(Telefono telefono) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "INSERT INTO telefono (celular, telefono) VALUES (?,?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, telefono.getCelular());
                pstmt.setString(2, telefono.getTelefono());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("Telefono insertado correctamente");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("Error al insertar telefono en la base de datos: " + e.getMessage());
                throw e;
            }
        }
    }


}

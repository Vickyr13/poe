package com.example.demo.database;

import com.example.demo.Model.Direccion;
import com.example.demo.Model.Empleado;

import java.sql.Connection;
import java.sql.SQLException;

public class DireccionDAO {

    public void insertDireccion(Direccion direccion) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "INSERT INTO direccion (detallada) VALUES (?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, direccion.getDetallada());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("Direccion insertada correctamente");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("Error al insertar direccion en la base de datos: " + e.getMessage());
                throw e;
            }
        }
    }

}

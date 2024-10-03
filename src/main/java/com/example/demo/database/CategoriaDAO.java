package com.example.demo.database;

import com.example.demo.Model.Categorias;
import com.example.demo.Model.Empleado;

import java.sql.Connection;
import java.sql.SQLException;

public class CategoriaDAO {

    public void insertarCategoria(Categorias categorias) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "INSERT INTO categorias (nombre_categoria, estado_categoria) VALUES (?,?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, categorias.getNombre_categoria());
                pstmt.setInt(2, categorias.getEstado_categoria());

                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("Categoria insertada correctamente");
                //   con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("Error al insertar nueva categoria " + e.getMessage());
                throw e;
            }
        }

    }
}

package com.example.demo.database;

import com.example.demo.Model.Categorias;
import com.example.demo.Model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    //seleccionar nombre de la categoria para mostrarlo en el combobox de platillo
    public List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT nombre_categoria FROM categorias"; // Ajusta al nombre correcto de la tabla/columna
        try (Connection con = conneection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("nombre_categoria"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorias;
    }
}

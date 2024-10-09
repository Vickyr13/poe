package com.example.demo.database;

import com.example.demo.Model.Categorias;
import com.example.demo.Model.Empleado;

import java.sql.*;
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

    //Mostrar los registros de catogias en la tabla
    public List<Categorias> obtenerCategoriasTable() throws SQLException {
        List<Categorias> categorias = new ArrayList<>();
        String query = "SELECT nombre_categoria, estado_categoria FROM categorias";

        try (Statement stmt = conneection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                //int id_categoria = rs.getInt("id_categoria");
                String nombreCategoria = rs.getString("nombre_categoria");
                int estado_categoria = rs.getInt("estado_categoria");

                // Usar el constructor sin el id_categoria
                Categorias categoria = new Categorias(nombreCategoria, estado_categoria);

                // Asignar el id_categoria usando el setter
                //categoria.setId_categoria(id_categoria);

                // Agregar la categoría a la lista
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}

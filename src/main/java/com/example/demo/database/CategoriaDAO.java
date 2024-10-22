package com.example.demo.database;

import com.example.demo.Model.Categorias;
import com.example.demo.Model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static ObservableList<Map> getCategoria() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "SELECT id_categoria, nombre_categoria, estado_categoria\n" +
                     "FROM categorias";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> categoria = new HashMap<>();
                categoria.put("id_categoria", rs.getString("id_categoria"));
                categoria.put("nombre_categoria", rs.getString("nombre_categoria"));
                categoria.put("estado_categoria", rs.getString("estado_categoria"));

                // Cambiar la lógica del estado del producto
                String estadoTexto = (rs.getInt("estado_categoria") == 1) ? "Activo" : "Inactivo";
                categoria.put("estado_categoria", estadoTexto);

                lista.add(categoria);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener la categoria: " + e.getMessage());
            throw e;
        }

        return lista;
    }

    public List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT nombre_categoria\n" +
                     "FROM categorias";
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

    public List<String> obtenerCategoriasFiltradas() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT nombre_categoria\n" +
                "FROM categorias where estado_categoria = 1";
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

    public void actualizarCategoria(int id_categoria, String nombre_categoria, int estado_categoria) throws SQLException {
        String query = "UPDATE categorias SET  nombre_categoria = ?, estado_categoria = ?\n" +
                        "WHERE id_categoria = ?";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {


            ps.setString(1, nombre_categoria);
            ps.setInt(2, estado_categoria);
            ps.setInt(3, id_categoria);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el producto: " + e.getMessage(), e);
        }
    }



}


package com.example.demo.database;

import com.example.demo.Model.Empleado;
import com.example.demo.Model.productos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class productosDAO {

    public productosDAO() {
    }

    public void insertarProducto(productos productos) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if (con != null) {
            //ejecutar la query para insertar empleado
            try {
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "INSERT INTO productos (id_categoria, nombre_producto, precio_unitario, descripcion, estado_producto) VALUES (?,?,?,?,?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, productos.getId_categoria());
                pstmt.setString(2, productos.getNombre_Producto());
                pstmt.setDouble(3, productos.getPrecio_unitario());
                pstmt.setString(4, productos.getDescriccios_Producto());
                pstmt.setInt(5, productos.getEstado_Productos());


                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("producto insertado correctamente");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("Error al insertar datos de producto" + e.getMessage());
                throw e;
            }
        }

    }


    public static void getAllProductos() throws SQLException {
        //establecer la conexion a la base de datos
        Connection con = conneection.getConnection();
        System.out.println("Conexión establecida: " + (con != null));

        //Verificamos la conexion a la base de datos
        if (con != null) {
            try {
                String query = "SELECT * FROM productos";
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                System.out.println("Query preparada: " + query);

                java.sql.ResultSet rs = pstmt.executeQuery();
                System.out.println("Query ejecutada correctamente.");

                while (rs.next()) {
                    int id_categoria = rs.getInt("id_categoria");
                    String nombre_Producto = rs.getString("nombre_producto");
                    double precio_unitario = rs.getDouble("precio_unitario");
                    String descripcion = rs.getString("descripcion");
                    int estado_producto = rs.getInt("estado_producto");

                    // Mostrar los datos en una ventana emergente
                    JOptionPane.showMessageDialog(null, "ID: " + ", Categoria: " + id_categoria + ", Nombre: " + nombre_Producto + ", Precio: " + precio_unitario);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener productos: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }





}





















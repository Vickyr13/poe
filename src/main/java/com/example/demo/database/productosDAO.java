package com.example.demo.database;

import com.example.demo.Model.Empleado;
import com.example.demo.Model.productos;

import java.sql.Connection;
import java.sql.SQLException;

public class productosDAO {


    public void insertarProducto(productos productos) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
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






}

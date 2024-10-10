package com.example.demo.database;

import com.example.demo.Model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//clase que tiene las querys para las gestion de empleados
public class EmpleadoDAO {

    //implementar los metodos para interectuar con la base de datos
    //metodo insertar empleado

    public void insertarEmpleado(Empleado empleado) throws SQLException {
        //establecer la conexion a la base de datos

        Connection con = conneection.getConnection();
        //Verificamos la conexion a la base de datos
        if(con!=null){
            //ejecutar la query para insertar empleado
            try{
                //query de insertar empleado
                //se hace de esta forma para evitar ataques de inyeccion SQL
                String query = "INSERT INTO empleados (nombre_Empleado, apellido_empleado, dui, email, " +
                        "direccion, telefono, fecha_contratacion, id_rol, estado_empleado, pin) VALUES (?,?,?,?,?,?,?,?,?,?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, empleado.getNombre_Empleado());
                pstmt.setString(2, empleado.getApellido_Empleado());
                pstmt.setString(3, empleado.getDUI());
                pstmt.setString(4, empleado.getCorreo_Empleado());
                pstmt.setString(5, empleado.getDireccion());
                pstmt.setString(6, empleado.getTelefono());
                pstmt.setString(7, String.valueOf(new java.sql.Date(empleado.getDate_Empleado().getTime())));
                pstmt.setInt(8, empleado.getId_rol());
                pstmt.setInt(9,empleado.getEstado_Empledo());
                pstmt.setString(10, empleado.getPin());
                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("Empleado insertado correctamente");
                //con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("Error al insertar datos de empleado" + e.getMessage());
                throw e;
            }
        }

    }

    // Método para obtener productos de la base de datos
    public ObservableList<Map> getEmpleados() throws SQLException {
        ObservableList<Map> lista = FXCollections.observableArrayList();

        String sql = "select id_empleado, nombre_empleado, apellido_empleado, dui, email, direccion, telefono," +
                " fecha_contratacion, r.nombre_rol, estado_empleado, pin \n" +
                "from empleados\n" +
                "join rol as r on empleados.id_rol = r.id_rol;";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                producto.put("nombre_empleado", rs.getString("nombre_empleado"));
                producto.put("apellido_empleado", rs.getString("apellido_empleado"));
                producto.put("dui", rs.getString("dui"));
                producto.put("email", rs.getString("email"));
                producto.put("direccion", rs.getString("direccion"));
                producto.put("telefono", rs.getString("telefono"));
                producto.put("fecha_contratacion", rs.getDate("fecha_contratacion"));
                producto.put("r.nombre_rol", rs.getString("r.nombre_rol"));
                producto.put("estado_empleado", rs.getInt("estado_empleado"));
                producto.put("pin", rs.getString("pin"));


                // Cambiar la lógica del estado del producto
                String estadoTexto = (rs.getInt("estado_empleado") == 1) ? "Activo" : "Inactivo";
                producto.put("estado_empleado", estadoTexto);

                lista.add(producto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener empleados: " + e.getMessage());
            throw e;
        }

        return lista;
    }

}
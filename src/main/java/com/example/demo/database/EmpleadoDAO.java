package com.example.demo.database;

import com.example.demo.Model.Empleado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
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
                "from empleados " +
                "join rol as r on empleados.id_rol = r.id_rol;";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> producto = new HashMap<>();
                // producto.put("id_rol"), rs.get
                producto.put("id_rol", rs.getString("nombre_rol"));
                producto.put("nombre_empleado", rs.getString("nombre_empleado"));
                producto.put("apellido_empleado", rs.getString("apellido_empleado"));
                producto.put("dui", rs.getString("dui"));
                producto.put("email", rs.getString("email"));
                producto.put("direccion", rs.getString("direccion"));
                producto.put("telefono", rs.getString("telefono"));
                producto.put("fecha_contratacion", rs.getDate("fecha_contratacion"));

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


    public static void pruebaconection(){
        System.out.println(
                "funciona"
        );
    }



    public void actualizarEmpleado
            (String nombre_empleado, String apellido_empleado, String dui, String mail,
             String direccion, String telefono, Date fecha_contratacion, int id_rol, int estado_empleado) throws SQLException {

        String query = "UPDATE empleados SET nombre_empleado = ?, apellido_empleado = ?, dui = ?, email = ?, " +
                "direccion = ?, telefono = ?, fecha_contratacion = ?, id_rol = ?, estado_empleado = ? WHERE dui = ?";

        try (Connection con = conneection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, nombre_empleado);           // nombre_empleado
            ps.setString(2, apellido_empleado);         // apellido_empleado
            ps.setString(3, dui);                       // dui
            ps.setString(4, mail);                      // mail
            ps.setString(5, direccion);                 // direccion
            ps.setString(6, telefono);                  // telefono
            ps.setDate(7, fecha_contratacion);          // fecha_contratacion (tipo java.sql.Date)
            ps.setInt(8, id_rol);                       // id_rol
            ps.setInt(9, estado_empleado);              // estado_empleado
            ps.setString(10, dui);

            ps.executeUpdate();

            System.out.println("String query = \"UPDATE empleados SET nombre_empleado = "+nombre_empleado+", apellido_empleado = "+apellido_empleado+", dui = "+dui+", " +
                    "email = "+mail+", \" +\n" +
                    " direccion = "+direccion+", telefono = "+telefono+", fecha_contratacion = "+fecha_contratacion+", id_rol = "+id_rol+", estado_empleado = "+estado_empleado+" WHERE dui = "+dui+"\";");
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar empleado: " + e.getMessage(), e);
        }
    }


}
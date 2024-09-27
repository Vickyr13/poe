package com.example.demo.database;

import com.example.demo.Model.Empleado;

import java.sql.Connection;
import java.sql.SQLException;

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
                String query = "INSERT INTO empleados (nombre_Empleado, apellido_empleado, dui, email, telefono, fecha_cotratacion, rol, estado_empleado) VALUES (?,?,?,?,?,?,?,?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, empleado.getNombre_Empleado());
                pstmt.setString(2, empleado.getApellido_Empleado());
                pstmt.setString(3, empleado.getDUI());
                pstmt.setString(4, empleado.getCorreo_Empleado());
                pstmt.setString(5, empleado.getTelefono_Empleado());
                pstmt.setString(6, String.valueOf(new java.sql.Date(empleado.getDate_Empleado().getTime())));
                pstmt.setString(7, empleado.getRol_Empleado());
                pstmt.setInt(8,empleado.getEstado_Empledo());
                //ejecutar la sentencia
                pstmt.execute();
                System.out.println("Empleado insertado correctamente");
                con.close(); //cerrar la conexion
            } catch (SQLException e) {
                System.out.println("Error mi tiyo Javier hizo mal el codigo: " + e.getMessage());
                throw e;
            }
        }

    }

    public static void pruebaconection(){
        System.out.println(
                "funciona"
        );
    }


}

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
                String query = "INSERT INTO empleados (nombre_Empleado, apellido_empleado, dui, email, " +
                        "id_direccion, id_telefono, fecha_contratacion, id_rol, estado_empleado, pin) VALUES (?,?,?,?,?,?,?,?,?,?)";

                //preparar la sentencia
                java.sql.PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, empleado.getNombre_Empleado());
                pstmt.setString(2, empleado.getApellido_Empleado());
                pstmt.setString(3, empleado.getDUI());
                pstmt.setString(4, empleado.getCorreo_Empleado());
                pstmt.setInt(5, empleado.getId_direccion());
                pstmt.setInt(6, empleado.getId_telefono());
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


    public static void pruebaconection(){
        System.out.println(
                "funciona"
        );
    }


}

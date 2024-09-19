package com.example.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static final String URL = "JDBC:mysql://localhost:3306/postventa";

    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
                System.out.println("Conexion a base de datos exitosa");
            }catch (SQLException | ClassNotFoundException e){
                System.out.println("Conexion a base de datos fallida");
                e.printStackTrace();
            }
        }
        return connection;
    }

    //metodo para cerrar la sesion de la base de datos
    public static void CLOSE_SESION(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}

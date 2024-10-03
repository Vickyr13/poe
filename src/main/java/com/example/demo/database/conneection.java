package com.example.demo.database;

import java.sql.Connection; // Usa java.sql.Connection
import java.sql.DriverManager;
import java.sql.SQLException;


public class conneection {

    private static final String URL = "jdbc:mysql://localhost:3306/postventa";
    private static final String USER = "root";
    private static final String PASSWORD = "Javi.1234";

    private static Connection connection; // Nombre de variable en minúscula

    public static Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al conectar a la base de datos", e);
            }
        }
        return connection;
    }

    public static void cerrarConexion() {
        try {
        if (connection != null && !connection.isClosed()) {
                connection.close();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.example.demo.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DashboardDAO {
    //Metodos para obtener datos del dashboard

    //Total de ordenes que solo han sido compledas

    // Método para obtener el total de órdenes completadas
    public int obtenerTotalOrdenesCompletadas() {
        int totalOrdenes = 0;
        String query = "SELECT COUNT(*) FROM ordenes WHERE estado = 'Completada'";

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalOrdenes = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalOrdenes;
    }

    // Método para obtener el total de ventas completadas en el último año
    public int obtenerTotalVentasAnual() {
        int totalVentas = 0;
        String query = """
                SELECT COUNT(*) 
                FROM ordenes 
                WHERE estado = 'Completada' 
                AND YEAR(fecha_orden) = YEAR(CURDATE());
                """;

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalVentas = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalVentas;
    }

    // Método para obtener el monto total de ventas completadas en el último año
    public double obtenerTotalMontoVentasAnual() {
        double totalMonto = 0.0;
        String query = """
                SELECT 
                    SUM(precio_total) 
                FROM 
                    ordenes
                WHERE 
                    estado = 'Completada'
                    AND YEAR(fecha_orden) = YEAR(CURDATE())
                GROUP BY 
                    YEAR(fecha_orden);
                """;

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalMonto = resultSet.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalMonto;
    }

    //Total de ventas en el último mes
    // Método para obtener el ingreso total del último mes
    public double obtenerIngresoUltimoMes() {
        double ingreso = 0.0;
        String query = """
                SELECT 
                    SUM(precio_total) 
                FROM 
                    ordenes
                WHERE 
                    estado = 'Completada'
                    AND fecha_orden >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH);
                """;

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                ingreso = resultSet.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingreso;
    }

    // Método para obtener el total de órdenes hechas
    public int obtenerTotalOrdenesHechas() {
        int totalOrdenes = 0;
        String query = """
                SELECT 
                    COUNT(*) 
                FROM 
                    ordenes;
                """;

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                totalOrdenes = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalOrdenes;
    }

    // Método para ejecutar la consulta SQL y obtener el monto total de ventas completadas.
    private double obtenerMontoTotalVentasCompletadas() {
        double montoTotal = 0.0;
        String query = """
                    SELECT 
                        SUM(precio_total) 
                    FROM 
                        ordenes
                    WHERE 
                        estado = 'Completada';
                """;

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                montoTotal = resultSet.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return montoTotal;
    }

    //Grafica
    // Método para obtener las ventas por mes para un año específico
    // Método para obtener las ventas por mes para un año específico
    public Map<String, Double> obtenerVentasPorMes(int año) {
        Map<String, Double> ventasPorMes = new HashMap<>();
        String query = """
                SELECT 
                    MONTH(fecha_orden) AS mes, 
                    SUM(precio_total) AS total_ventas
                FROM 
                    ordenes
                WHERE 
                    YEAR(fecha_orden) = ?
                    AND estado = 'Completada'
                GROUP BY 
                    mes
                ORDER BY 
                    mes;
                """;

        // Conexión directa en la consulta
        try (Connection connection = conneection.getConnection();  // Asegúrate de que `conneection` sea accesible aquí
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, año);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int mes = resultSet.getInt("mes");
                    double totalVentas = resultSet.getDouble("total_ventas");
                    ventasPorMes.put(mesToString(mes), totalVentas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores
        }

        return ventasPorMes;
    }

    // Método para convertir el mes a texto
    private String mesToString(int mes) {
        switch (mes) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Mes Desconocido";
        }
    }

    // Método para obtener el top 3 de empleados que más ventas han realizado
    public ObservableList<PieChart.Data> obtenerTopEmpleadosVentas() {
        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
        String query = """
            SELECT 
                e.nombre_empleado AS mesero, 
                SUM(o.precio_total) AS monto_total
            FROM 
                ordenes o
            JOIN 
                detalle_ordenes d ON o.id_orden = d.id_orden
            JOIN 
                empleados e ON d.id_mesa IN (
                    SELECT id_mesa 
                    FROM mesa 
                    WHERE id_mesa IN (
                        SELECT DISTINCT id_mesa 
                        FROM detalle_ordenes 
                        JOIN empleados emp ON emp.id_rol = 1 -- 1 = Mesero
                    )
                )
            WHERE 
                e.id_rol = 1 -- Solo meseros
                AND o.estado = 'Completada'
            GROUP BY 
                e.nombre_empleado
            ORDER BY 
                monto_total DESC
            LIMIT 3;
        """;

        try (Connection connection = conneection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String mesero = resultSet.getString("mesero");
                double montoTotal = resultSet.getDouble("monto_total");
                datos.add(new PieChart.Data(mesero, montoTotal));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datos;
    }
}

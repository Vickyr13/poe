package com.example.demo.Model;

public class Ordenes {


    private int id_orden;
    private int fecha_orden;
    private double precio_orden;
    private String estado_orden;


    // Constructor vacío y constructor con parámetros para la clase Ordenes
    public Ordenes() {
    }

    public Ordenes(double precio_orden, int fecha_orden, String estado_orden) {
        this.precio_orden = precio_orden;
        this.fecha_orden = fecha_orden;
        this.estado_orden = estado_orden;
    }

    // Constructor con parámetros para la clase Ordenes
    public Ordenes(double precio_orden, String estado_orden) {
        this.precio_orden = precio_orden;
        this.estado_orden = estado_orden;
    }

    // getters and setters


    public double getprecio_orden() {
        return precio_orden;
    }


    public void setPrecio_orden(double precio_orden) {
        this.precio_orden = precio_orden;
    }
    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(int fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getEstado_orden() {
        return estado_orden;
    }

    public void setEstado_orden(String estado_orden) {
        this.estado_orden = estado_orden;
    }
}

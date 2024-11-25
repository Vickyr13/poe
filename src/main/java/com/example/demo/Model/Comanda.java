package com.example.demo.Model;

public class Comanda {
    private int cantidad;
    private String nombreProducto;
    private String mensaje;

    public Comanda(int cantidad, String nombreProducto, String mensaje) {
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.mensaje = mensaje;
    }

    // Getters y Setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

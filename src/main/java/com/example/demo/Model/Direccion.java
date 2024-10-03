package com.example.demo.Model;

public class Direccion {

    private int id_direccion;
    private String detallada;

    public Direccion() {
    }

    public Direccion(String detallada) {
        this.detallada = detallada;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getDetallada() {
        return detallada;
    }

    public void setDetallada(String detallada) {
        this.detallada = detallada;
    }
}

package com.example.demo.Model;

public class Detalles_ordenes {
    private int id_detalles_ordenes;
    private int id_orden;
    private int id_Producto;
    private int id_mesa;
    private int cantidad;
    private double sub_cantidad;
    private int id_empleados;
    private String mesaje;

    public Detalles_ordenes(int id_orden, int id_Producto, int id_mesa, int cantidad, double sub_cantidad, int id_empleados , String mesaje) {
        this.id_orden = id_orden;
        this.id_Producto = id_Producto;
        this.id_mesa = id_mesa;
        this.cantidad = cantidad;
        this.sub_cantidad = sub_cantidad;
        this.id_empleados = id_empleados;
        this.mesaje = mesaje;
    }

    // getters y setters

    public int getId_detalles_ordenes() {
        return id_detalles_ordenes;
    }

    public void setId_detalles_ordenes(int id_detalles_ordenes) {
        this.id_detalles_ordenes = id_detalles_ordenes;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSub_cantidad() {
        return sub_cantidad;
    }

    public void setSub_cantidad(double sub_cantidad) {
        this.sub_cantidad = sub_cantidad;
    }
    public int getId_mesa() {
        return id_mesa;
    }
    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_empleados() {
        return id_empleados;
    }

    public void setId_empleados(int id_empleados) {
        this.id_empleados = id_empleados;
    }

    public String getMesaje() {
        return mesaje;
    }

    public void setMesaje(String mesaje) {
        this.mesaje = mesaje;
    }

}

package com.example.demo.Model;

public class productos {

    private int  id_productos;
    private int id_categoria;
    private String nombre_Producto;
    private double precio_unitario;
    private String descriccios_Producto;
    private int estado_Productos;
    private int cantidad;



    public productos(int id_categoria, String nombre_Producto, double precio_unitario, String descriccios_Producto, int estado_Productos) {
        this.id_categoria = id_categoria;
        this.nombre_Producto = nombre_Producto;
        this.precio_unitario = precio_unitario;
        this.descriccios_Producto = descriccios_Producto;
        this.estado_Productos = estado_Productos;
    }

    //costrctor par la parte de meseros

    public productos(int id_producto,String nombre_Producto, String descriccios_Producto, double precio_unitario) {
        this.id_productos = id_producto;
        this.nombre_Producto = nombre_Producto;
        this.descriccios_Producto = descriccios_Producto;
        this.precio_unitario = precio_unitario;
    }



    // los geter y los seter
    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getDescriccios_Producto() {
        return descriccios_Producto;
    }

    public void setDescriccios_Producto(String descriccios_Producto) {
        this.descriccios_Producto = descriccios_Producto;
    }

    public int getEstado_Productos() {
        return estado_Productos;
    }

    public void setEstado_Productos(int estado_Productos) {
        this.estado_Productos = estado_Productos;
    }


    //----------------------------------------------------------------

}

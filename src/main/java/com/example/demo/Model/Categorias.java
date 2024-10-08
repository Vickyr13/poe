package com.example.demo.Model;

import com.mysql.cj.conf.IntegerProperty;

public class Categorias {
    private int id_categoria;
    private String nombre_categoria;
    private int estado_categoria;



    // Constructor vacío y constructor con parámetros para la clase Categorias
    public Categorias() {
    }


    public Categorias(String nombre_categoria, int estado_categoria) {

        this.nombre_categoria = nombre_categoria;
        this.estado_categoria = estado_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public int getEstado_categoria() {
        return estado_categoria;
    }

    public void setEstado_categoria(int estado_categoria) {
        this.estado_categoria = estado_categoria;
    }
}

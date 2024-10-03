package com.example.demo.Model;

public class Telefono {
    private int id_telefono;
    private String celular;
    private String telefono;

    public Telefono() {
    }

    public Telefono( String celular, String telefono) {

        this.celular = celular;
        this.telefono = telefono;
    }

    public int getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(int id_telefono) {
        this.id_telefono = id_telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

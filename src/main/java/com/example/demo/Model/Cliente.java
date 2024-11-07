package com.example.demo.Model;

public class Cliente {

    private int id_Cliente;
    private String direccion_Cliente;
    private String telefono_Cliente;
    private String nombre_Cliente;
    private String apellido_Cliente;


    public Cliente(String direccion_Cliente, String telefono_Cliente, String nombre_Cliente, String apellido_Cliente) {
        this.direccion_Cliente = direccion_Cliente;
        this.telefono_Cliente = telefono_Cliente;
        this.nombre_Cliente = nombre_Cliente;
        this.apellido_Cliente = apellido_Cliente;
    }


    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getDireccion_Cliente() {
        return direccion_Cliente;
    }

    public void setDireccion_Cliente(String direccion_Cliente) {
        this.direccion_Cliente = direccion_Cliente;
    }

    public String getTelefono_Cliente() {
        return telefono_Cliente;
    }

    public void setTelefono_Cliente(String telefono_Cliente) {
        this.telefono_Cliente = telefono_Cliente;
    }

    public String getNombre_Cliente() {
        return nombre_Cliente;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        this.nombre_Cliente = nombre_Cliente;
    }

    public String getApellido_Cliente() {
        return apellido_Cliente;
    }

    public void setApellido_Cliente(String apellido_Cliente) {
        this.apellido_Cliente = apellido_Cliente;
    }
}

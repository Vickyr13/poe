package com.example.demo.Model;
//esta clase representa los objetos de empleados
import java.util.Date;

public class Empleado {

    private int id_Empleado;
    private String nombre_Empleado;
    private String apellido_Empleado;
    private String DUI;
    private String correo_Empleado;
    private String direccion;
    private String telefono;
    private Date date_Empleado;
    private int id_rol;
    private int estado_Empledo;
    private String pin;


    public Empleado() {

    }

    public Empleado(String nombre_Empleado, String apellido_Empleado, String DUI, String correo_Empleado, String direccion, String telefono, Date date_Empleado, int id_rol, int estado_Empledo, String pin) {
        //this.id_Empleado = id_Empleado;
        this.nombre_Empleado = nombre_Empleado;
        this.apellido_Empleado = apellido_Empleado;
        this.DUI = DUI;
        this.correo_Empleado = correo_Empleado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.date_Empleado = date_Empleado;
        this.id_rol = id_rol;
        this.estado_Empledo = estado_Empledo;
        this.pin = pin;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_Empleado() {
        return id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        this.id_Empleado = id_Empleado;
    }

    public String getNombre_Empleado() {
        return nombre_Empleado;
    }

    public void setNombre_Empleado(String nombre_Empleado) {
        this.nombre_Empleado = nombre_Empleado;
    }

    public String getApellido_Empleado() {
        return apellido_Empleado;
    }

    public void setApellido_Empleado(String apellido_Empleado) {
        this.apellido_Empleado = apellido_Empleado;
    }

    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public String getCorreo_Empleado() {
        return correo_Empleado;
    }

    public void setCorreo_Empleado(String correo_Empleado) {
        this.correo_Empleado = correo_Empleado;
    }


    public Date getDate_Empleado() {
        return date_Empleado;
    }

    public void setDate_Empleado(Date date_Empleado) {
        this.date_Empleado = date_Empleado;
    }



    public int getEstado_Empledo() {
        return estado_Empledo;
    }

    public void setEstado_Empledo(int estado_Empledo) {
        this.estado_Empledo = estado_Empledo;
    }

    public String getPin() {return pin;}

    public void setPin(String pin) {this.pin = pin;}

}
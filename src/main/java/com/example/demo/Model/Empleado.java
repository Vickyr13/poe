package com.example.demo.Model;
//esta clase representa los objetos de empleados
import java.util.Date;

public class Empleado {

    private int id_Empleado;
    private String nombre_Empleado;
    private String apellido_Empleado;
    private String DUI;
    private String correo_Empleado;
    private String telefono_Empleado;
    private Date date_Empleado;
    private String rol_Empleado;
    private int estado_Empledo;


    public Empleado() {

    }

    public Empleado(String nombre_Empleado, String apellido_Empleado, String DUI, String correo_Empleado, String telefono_Empleado, Date date_Empleado, String rol_Empleado, int estado_Empledo) {
        this.nombre_Empleado = nombre_Empleado;
        this.apellido_Empleado = apellido_Empleado;
        this.DUI = DUI;
        this.correo_Empleado = correo_Empleado;
        this.telefono_Empleado = telefono_Empleado;
        this.date_Empleado = date_Empleado;
        this.rol_Empleado = rol_Empleado;
        this.estado_Empledo = estado_Empledo;
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

    public String getTelefono_Empleado() {
        return telefono_Empleado;
    }

    public void setTelefono_Empleado(String telefono_Empleado) {
        this.telefono_Empleado = telefono_Empleado;
    }

    public Date getDate_Empleado() {
        return date_Empleado;
    }

    public void setDate_Empleado(Date date_Empleado) {
        this.date_Empleado = date_Empleado;
    }

    public String getRol_Empleado() {
        return rol_Empleado;
    }

    public void setRol_Empleado(String rol_Empleado) {
        this.rol_Empleado = rol_Empleado;
    }

    public int getEstado_Empledo() {
        return estado_Empledo;
    }

    public void setEstado_Empledo(int estado_Empledo) {
        this.estado_Empledo = estado_Empledo;
    }
}

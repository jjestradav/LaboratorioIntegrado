package com.example.laboratorio3.entity;

import java.io.Serializable;
import java.util.Date;

public class Alumno implements  Serializable {

    private String cedula;
    private String nombre;
    private int telefono;
    private String email;
    private Date fechaNacimiento;

    public Alumno() {
    }

    public Alumno(String cedula, String nombre, int telefono, String email, Date fechaNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Alumno(String cedula) {
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Alumno{" + "cedula=" + cedula + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + '}';
    }


}
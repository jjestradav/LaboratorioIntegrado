package com.example.laboratorio3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Usuario  implements Serializable{


    private String cedula;
    private String password;
    private String rol;

    public Usuario() {
    }


    public Usuario(String cedula, String password, String rol) {
        this.cedula = cedula;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String cedula, String rol) {
        this.cedula = cedula;
        this.password = null;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", password=" + password + ", rol=" + rol + '}';
    }





}
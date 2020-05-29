package com.example.laboratorio3.entity.DTO;

import java.io.Serializable;

public class AlumnoDTO implements Serializable {
    private String cedula;
    private String nombre;

    public AlumnoDTO() {
    }

    public AlumnoDTO(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
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
}

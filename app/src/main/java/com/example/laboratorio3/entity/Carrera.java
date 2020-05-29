package com.example.laboratorio3.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrera implements Serializable {

    private String codigo;
    private String nombre;
    private String titulo;
    List<Curso> cursos;

    public Carrera() {
        this.cursos=new ArrayList<>();
    }

    public Carrera(String codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos=new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Carrera{" + "codigo=" + codigo + ", nombre=" + nombre + ", titulo=" + titulo + ", cursos=" + cursos + '}';
    }




}
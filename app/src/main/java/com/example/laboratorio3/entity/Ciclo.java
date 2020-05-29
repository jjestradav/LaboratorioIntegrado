package com.example.laboratorio3.entity;

import java.io.Serializable;
import java.util.Date;

public class Ciclo implements  Serializable {

    private int codigo;
    private int anho;
    private char numeroCiclo;
    private Date fechaInicio;
    private Date fechaFinal;

    public Ciclo() {
    }

    public Ciclo(int codigo, int anho, char numeroCiclo, Date fechaInicio, Date fechaFinal) {
        this.codigo = codigo;
        this.anho = anho;
        this.numeroCiclo = numeroCiclo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    public Ciclo(int codigo, char numeroCiclo, int anho) {

        this.codigo=codigo;
        this.anho = anho;
        this.numeroCiclo = numeroCiclo;
        this.fechaInicio = null;
        this.fechaFinal = null;
    }

    public Ciclo(int codigo) {

        this.codigo=codigo;
        this.fechaInicio = null;
        this.fechaFinal = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public char getNumeroCiclo() {
        return numeroCiclo;
    }

    public void setNumeroCiclo(char numeroCiclo) {
        this.numeroCiclo = numeroCiclo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public String toString() {
        return "Ciclo "+this.numeroCiclo+" "+this.anho;
    }


}
package com.example.laboratorio3.entity.DTO;

import java.io.Serializable;

public class GrupoAlumnoDTO implements Serializable {
    private int grupo;
    private AlumnoDTO alumno;
    private  float nota;

    public GrupoAlumnoDTO(int grupo, AlumnoDTO alumno, float nota) {
        this.grupo = grupo;
        this.alumno = alumno;
        this.nota = nota;
    }

    public GrupoAlumnoDTO() {
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String mostrarEstudiante(){
        return ""+this.getAlumno().getNombre()+"\n"+"Nota: "+this.nota;
    }
}

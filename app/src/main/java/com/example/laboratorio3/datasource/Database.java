package com.example.laboratorio3.datasource;

import android.util.Log;

import com.example.laboratorio3.entity.Alumno;
import com.example.laboratorio3.entity.Ciclo;
import com.example.laboratorio3.entity.Curso;
import com.example.laboratorio3.entity.Grupo;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.entity.Profesor;
import com.example.laboratorio3.entity.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Database {
   private static Calendar calendar1 = Calendar.getInstance();


    Calendar calendar2 = Calendar.getInstance();
    Calendar calendar3 = Calendar.getInstance();
    Calendar calendar4 = Calendar.getInstance();
    private static Alumno alumno1=new Alumno("1111","Jonathan",77767574,"jonathan@alumno.com",new Date());
    private static Alumno alumno2=new Alumno("2222","Alejandro",88878787,"alejandro@alumno.com",new Date());
    private static Alumno alumno3=new Alumno("3333","Jose",81828384,"jose@alumno.com",new Date());
    private static Alumno alumno4=new Alumno("4444","Pablo",85868788,"pablo@alumno.com",new Date());
    public static String currentUser="";
    public static List<Ciclo> getCiclos=Ciclos();
    private static Usuario us1=new Usuario("123456789","profesor","profesor");
    private static Usuario us2=new Usuario("987654321","profesor","profesor");
    private static Usuario us3=new Usuario("0000","superuser","superuser");
    private static Profesor profe1=new Profesor("123456789","Juan Perez","juanperez@profe.com",88878685);
    private static Profesor profe2=new Profesor("987654321","Juana Salas","juanasalas@profe.com",88898081);
    private static Curso curso1=new Curso(1,"Programacion 1",4,4);
    private static Curso curso2=new Curso(2,"Programacion 2",4,4);
    private static Curso curso3=new Curso(3,"Programacion 3",4,4);
    private static Curso curso4=new Curso(4,"Programacion 4",4,4);
    private static Curso curso5=new Curso(5,"Estructuras de Datos",4,4);
    private static Curso curso6=new Curso(6,"Bases de Datos 1",4,4);
    private static Grupo grupo1= new Grupo(1,getCiclos.get(0),curso1,profe1,"L-J 10:00-12:00",1);
    private static Grupo grupo2= new Grupo(2,getCiclos.get(0),curso2,profe1,"M-V 10:00-12:00",1);
    private static Grupo grupo3= new Grupo(3,getCiclos.get(0),curso3,profe1,"K 8:00-11:40",1);
    private static Grupo grupo4= new Grupo(4,getCiclos.get(0),curso4,profe2,"L-J 10:00-12:00",1);
    private static Grupo grupo5= new Grupo(5,getCiclos.get(0),curso5,profe2,"M-V 10:00-12:00",1);
    private static Grupo grupo6= new Grupo(6,getCiclos.get(0),curso6,profe2,"K 8:00-11:40",1);
    private static GrupoAlumno grupoAlumno1=new GrupoAlumno(grupo1,alumno1,0.0f);
    private static GrupoAlumno grupoAlumno2=new GrupoAlumno(grupo1,alumno2,0.0f);
    private static GrupoAlumno grupoAlumno3=new GrupoAlumno(grupo1,alumno3,0.0f);
    private static GrupoAlumno grupoAlumno4=new GrupoAlumno(grupo1,alumno4,0.0f);
    public static List<Grupo> grupos=getGrupos();
    public static List<GrupoAlumno> getGrupoAlumnos=new ArrayList<GrupoAlumno>() {

        {
            add(grupoAlumno1);
            add(grupoAlumno2);
            add(grupoAlumno3);
            add(grupoAlumno4);
        }
    };

    public static List<Curso> getCursos=new ArrayList<Curso>() {

        {
            add(curso1);
            add(curso2);
            add(curso3);
            add(curso4);
            add(curso5);
            add(curso6);
        }
    };





    public static List<Curso> cursosPorProfesor(String cedula,int ciclo){
        List<Curso> result= new ArrayList<>();
        List<Grupo> _grupos=getGrupos();
        Log.v("GRUPOS",""+_grupos);
        for(Grupo grupo: _grupos){
            if(grupo.getProfesor().getCedula().equals(cedula)&&grupo.getCiclo().getCodigo()==ciclo)
                result.add(grupo.getCurso());
        }
        return result;

    }

    public static List<Grupo> gruposPorProfesor(String cedula, int curso){
        List<Grupo> result=new ArrayList<>();
        List<Grupo> _grupos=getGrupos();
        for(Grupo grupo: _grupos){
            if(grupo.getProfesor().getCedula().equals(cedula)&&grupo.getCurso().getCodigo()==curso)
                result.add(grupo);
        }
        return result;
    }


    public static List<Usuario> Usuarios(){
        List<Usuario> result=new ArrayList<>();
        result.add(us1);
        result.add(us2);
        result.add(us3);

        return result;
    }

    public static List<Grupo> getGrupos(){
        List<Grupo> result= new ArrayList<>();
        result.add(grupo1);
        result.add(grupo2);
        result.add(grupo3);
        result.add(grupo4);
        result.add(grupo5);
        result.add(grupo6);
        return result;
    }

    private static List<Ciclo> Ciclos(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2020,1,10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020,5,10);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2020,6,10);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2020,10,10);
        List<Ciclo> result=new ArrayList<>();
    Ciclo ciclo1=new Ciclo(1,2020,'1',calendar1.getTime(),calendar2.getTime());
        Ciclo ciclo2=new Ciclo(2,2020,'2',calendar3.getTime(),calendar4.getTime());

        result.add(ciclo1);
        result.add(ciclo2);

        return result;
    }

    public static List<Ciclo> getCurrentCiclo(){

        List<Ciclo> ciclos=getCiclos;



        List<Ciclo> result=new ArrayList<>();
        Date now= new Date();
        Log.v("NOW",""+now);
        for(Ciclo ciclo : ciclos){
            boolean res=now.compareTo(ciclo.getFechaInicio())>0;
            Log.v("FECHA INICIO",""+ciclo.getFechaInicio());
            Log.v("RESULT",""+res);
            if(ciclo.getFechaFinal().compareTo(now)>=0 &&now.compareTo(ciclo.getFechaInicio())>=0){
                result.add(ciclo);
            }
        }
        return result;
    }

}

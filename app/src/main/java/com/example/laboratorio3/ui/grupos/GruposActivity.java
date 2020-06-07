package com.example.laboratorio3.ui.grupos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.AsyncResponse;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.datasource.NetworkConnection;
import com.example.laboratorio3.entity.Ciclo;
import com.example.laboratorio3.entity.Curso;
import com.example.laboratorio3.entity.Grupo;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.entity.Profesor;
import com.example.laboratorio3.ui.cursos.CursosActivity;
import com.example.laboratorio3.ui.grupoAlumnos.GrupoAlumnoActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GruposActivity extends AppCompatActivity {

    private String rol;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        ImageView btnGrupos=findViewById(R.id.gruposBtn);
        this.rol=getIntent().getStringExtra("rol");
        this.id=getIntent().getStringExtra("id");
        populateSpinner();
        btnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ON CLICK","ON CLICK");
                onClickAction();
            }
        });
    }



    private void populateSpinner(){

        int codigoCurso=getIntent().getIntExtra("curso",-1);
        String url="http://192.168.0.13:9090/Lab1/getGrupos?curso="+codigoCurso+"&role="+this.rol;
        if(!this.rol.equals("superuser"))
            url+="&profe="+this.id;
        NetworkConnection connection=new NetworkConnection(url, new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    JSONArray array = new JSONArray(output);
                    List<Grupo> result= new ArrayList<>();
                    for(int i=0; i<array.length(); i++){
                        Grupo grupo= new Grupo();

                        grupo.setCodigo(array.getJSONObject(i).getInt("codigo"));
                        grupo.setHorario(array.getJSONObject(i).getString("horario"));
                        grupo.setNumeroGrupo(array.getJSONObject(i).getInt("numeroGrupo"));
                        JSONObject cicloJson= array.getJSONObject(i).getJSONObject("ciclo");
                        Ciclo ciclo= new Ciclo();
                        ciclo.setCodigo(cicloJson.getInt("codigo"));
                        JSONObject cursoJson= array.getJSONObject(i).getJSONObject("curso");
                        Curso cur= new Curso();
                        cur.setCodigo(cursoJson.getInt("codigo"));
                        Profesor profe= new Profesor();
                        JSONObject profeJson= array.getJSONObject(i).getJSONObject("profesor");
                        profe.setCedula(profeJson.getString("cedula"));
                        grupo.setCiclo(ciclo);
                        grupo.setCurso(cur);
                        grupo.setProfesor(profe);
                        result.add(grupo);


                    }

                    ArrayAdapter<Grupo> spinnerArrayAdapter = new ArrayAdapter<Grupo>(
                            getApplicationContext(), android.R.layout.simple_spinner_item, result);
                    spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                    Spinner cursosSpinner=  findViewById(R.id.spinnerGrupos);
                    cursosSpinner.setAdapter(spinnerArrayAdapter);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        connection.execute(NetworkConnection.GET);

    }

    private void onClickAction(){
        Intent GrupoAlumno= new Intent(getApplicationContext(), GrupoAlumnoActivity.class);
        Spinner spinner = (Spinner)findViewById(R.id.spinnerGrupos);
        Grupo grupo= (Grupo) spinner.getSelectedItem();
        GrupoAlumno.putExtra("grupo",grupo.getCodigo());
        startActivity(GrupoAlumno);
    }

}

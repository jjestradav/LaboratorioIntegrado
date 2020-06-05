package com.example.laboratorio3.ui.cursos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.AsyncResponse;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.datasource.NetworkConnection;
import com.example.laboratorio3.entity.Curso;
import com.example.laboratorio3.ui.ciclos.CiclosActivity;
import com.example.laboratorio3.ui.grupos.GruposActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CursosActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private String rol;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        Button cursosBtn=(Button) findViewById(R.id.cursosBtn);
        this.rol=getIntent().getStringExtra("rol");
        this.id=getIntent().getStringExtra("id");
       populateSpinner();
        cursosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ON CLICK","ON CLICK");
                onClickAction();
            }
        });

    }


    private void populateSpinner(){

        progressDialog = new ProgressDialog(CursosActivity.this);
        progressDialog.setMessage("Por favor espere");
        progressDialog.setCancelable(false);
        progressDialog.show();
        int codigoCiclo=getIntent().getIntExtra("ciclo",-1);
        String url="http://192.168.0.13:9090/Lab1/getCursos?ciclo="+codigoCiclo+"&role="+this.rol;
        if(!rol.equals("superuser"))
               url+="&profe="+this.id;

        NetworkConnection connection= new NetworkConnection(url, new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    List<Curso> result = new ArrayList<>();
                    JSONArray array= new JSONArray(output);
                    for(int i=0; i<array.length();i++){
                        Curso cur= new Curso();
                        cur.setCodigo(array.getJSONObject(i).getInt("codigo"));
                        cur.setNombre(array.getJSONObject(i).getString("nombre"));
                        result.add(cur);
                    }

                    ArrayAdapter<Curso> spinnerArrayAdapter = new ArrayAdapter<Curso>(
                            getApplicationContext(), android.R.layout.simple_spinner_item, result);
                    spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                    Spinner cursosSpinner=  findViewById(R.id.spinnerCursos);
                    cursosSpinner.setAdapter(spinnerArrayAdapter);
                    progressDialog.dismiss();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        connection.execute(NetworkConnection.GET);

    }



    private void onClickAction(){
        Spinner spinner = (Spinner)findViewById(R.id.spinnerCursos);
        Curso curso= (Curso)spinner.getSelectedItem();
        Intent GruposActivity= new Intent(getApplicationContext(), GruposActivity.class);

        GruposActivity.putExtra("curso", curso.getCodigo());
        GruposActivity.putExtra("rol",this.rol);
        GruposActivity.putExtra("id",this.id);
        startActivity(GruposActivity);
    }
}

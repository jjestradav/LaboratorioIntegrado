package com.example.laboratorio3.ui.ciclos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.laboratorio3.NavDrawerActivity;
import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.AsyncResponse;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.datasource.NetworkConnection;
import com.example.laboratorio3.entity.Ciclo;
import com.example.laboratorio3.ui.cursos.CursosActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CiclosActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private String rol;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclos);
        Button ciclosButton = (Button)findViewById(R.id.ciclosBtn);

        this.populateSpinner();

        ciclosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("ON CLICK","ON CLICK");
               onClickAction();
            }
        });


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    private void populateSpinner(){
        progressDialog = new ProgressDialog(CiclosActivity.this);
        progressDialog.setMessage("Por favor espere");
        progressDialog.setCancelable(false);
        progressDialog.show();

        rol=getIntent().getStringExtra("rol");
        username=getIntent().getStringExtra("id");
        NetworkConnection connection= new NetworkConnection("http://192.168.0.14:9090/Lab1/getCiclos", new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try {
                    List<Ciclo> result= new ArrayList<>();
                    JSONArray array = new JSONArray(output);
                    for(int i=0; i<array.length(); i++){
                        Ciclo ciclo= new Ciclo();
                        ciclo.setCodigo(array.getJSONObject(i).getInt("codigo"));
                        ciclo.setAnho(array.getJSONObject(i).getInt("anho"));
                        ciclo.setNumeroCiclo(array.getJSONObject(i).getString("numeroCiclo").charAt(0));
                        result.add(ciclo);
                    }

                    ArrayAdapter<Ciclo> spinnerArrayAdapter = new ArrayAdapter<Ciclo>(
                            getApplicationContext(), android.R.layout.simple_spinner_item, result);
                    spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
                    Spinner ciclosSpinner=  findViewById(R.id.spinnerCiclos);
                    ciclosSpinner.setAdapter(spinnerArrayAdapter);
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
        Spinner spinner = (Spinner)findViewById(R.id.spinnerCiclos);
         Ciclo ciclo= (Ciclo)spinner.getSelectedItem();
        Intent cursosActivity= new Intent(getApplicationContext(), CursosActivity.class);
        cursosActivity.putExtra("ciclo", ciclo.getCodigo());
        cursosActivity.putExtra("rol", this.rol);
        cursosActivity.putExtra("id", this.username);
        startActivity(cursosActivity);
    }
}

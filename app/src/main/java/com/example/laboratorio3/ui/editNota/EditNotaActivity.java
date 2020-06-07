package com.example.laboratorio3.ui.editNota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.AsyncResponse;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.datasource.NetworkConnection;
import com.example.laboratorio3.entity.DTO.GrupoAlumnoDTO;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.ui.grupoAlumnos.GrupoAlumnoActivity;

import org.json.JSONObject;

public class EditNotaActivity extends AppCompatActivity {

    private ImageView btnEdit;
    private TextView cedulaText;
    private TextView nombreText;
    private EditText nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_edit_nota);
      fillText();
      btnEdit=(ImageView) findViewById(R.id.saveBtn);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNota();
            }
        });
    }

    private void fillText(){
        cedulaText=findViewById(R.id.cedulaText);
        nombreText=findViewById(R.id.nombreText);
        nota=findViewById(R.id.notaText);
        GrupoAlumnoDTO grupoAlumno=(GrupoAlumnoDTO) getIntent().getSerializableExtra("grupoAlumno");
        cedulaText.setText(grupoAlumno.getAlumno().getCedula());
        nombreText.setText(grupoAlumno.getAlumno().getNombre());
        nota.setText(""+grupoAlumno.getNota());
    }

    private void setNota(){
        try {
            nota = findViewById(R.id.notaText);
            GrupoAlumnoDTO grupoAlumno = (GrupoAlumnoDTO) getIntent().getSerializableExtra("grupoAlumno");
            grupoAlumno.setNota(Float.parseFloat(nota.getText().toString()));
            final int grupo=grupoAlumno.getGrupo();
            JSONObject obj = new JSONObject();
            JSONObject objAlumno= new JSONObject();
            objAlumno.put("cedula",grupoAlumno.getAlumno().getCedula());
            objAlumno.put("nombre",grupoAlumno.getAlumno().getNombre());

            obj.put("grupo",grupoAlumno.getGrupo());
            obj.put("nota",grupoAlumno.getNota());
            obj.put("alumno", objAlumno);

            String url = "http://192.168.0.13:9090/Lab1/updateNota";
            NetworkConnection connection = new NetworkConnection(url, new AsyncResponse() {
                @Override
                public void processFinish(String output) {
                    boolean result= Boolean.valueOf(output);
                    if(result)
                        Toast.makeText(getApplicationContext(), "Nota Actualizada Correctamente", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_LONG).show();

                    Intent grupoAlumnoAcitivity = new Intent(EditNotaActivity.this, GrupoAlumnoActivity.class);
                    grupoAlumnoAcitivity.putExtra("grupo",grupo);
                    startActivity(grupoAlumnoAcitivity);
                    finish();
                }
            });

            connection.execute(NetworkConnection.PUT,obj.toString());

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

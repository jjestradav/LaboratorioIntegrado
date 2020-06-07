package com.example.laboratorio3.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.laboratorio3.NavDrawerActivity;
import com.example.laboratorio3.R;
import com.example.laboratorio3.datasource.AsyncResponse;
import com.example.laboratorio3.datasource.Dao;
import com.example.laboratorio3.datasource.NetworkConnection;
import com.example.laboratorio3.ui.cursos.CursosActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ImageView loginBtn;
    private  Dao dao= Dao.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameTxt=username.getText().toString();
                String passwordTxt=password.getText().toString();
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("username", usernameTxt);
                    obj.put("password", passwordTxt);
                    NetworkConnection connection= new NetworkConnection("http://192.168.0.13:9090/Lab1/login", new AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            if(!output.isEmpty()){
                                Intent navActivity = new Intent(LoginActivity.this, NavDrawerActivity.class);
                                navActivity.putExtra("rol", output);
                                Log.v("ROL",output);
                                navActivity.putExtra("id",usernameTxt);
                                startActivity(navActivity);
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Por favor revise los datos", Toast.LENGTH_LONG).show();
                        }
                    });
                    connection.execute(NetworkConnection.POST,obj.toString());



                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}

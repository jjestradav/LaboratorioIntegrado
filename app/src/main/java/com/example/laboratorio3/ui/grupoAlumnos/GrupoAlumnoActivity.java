package com.example.laboratorio3.ui.grupoAlumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.laboratorio3.R;
import com.example.laboratorio3.adapter.GrupoAlumnoAdapter;
import com.example.laboratorio3.datasource.AsyncResponse;
import com.example.laboratorio3.datasource.Database;
import com.example.laboratorio3.datasource.NetworkConnection;
import com.example.laboratorio3.entity.DTO.AlumnoDTO;
import com.example.laboratorio3.entity.DTO.GrupoAlumnoDTO;
import com.example.laboratorio3.entity.GrupoAlumno;
import com.example.laboratorio3.helper.RecyclerItemTouchHelper;
import com.example.laboratorio3.ui.editNota.EditNotaActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GrupoAlumnoActivity extends AppCompatActivity implements  RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,  GrupoAlumnoAdapter.GrupoAlumnoAdapterAdapterListener,
        GrupoAlumnoAdapter.GrupoAlumnoClick{

    SwipeRefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;
    GrupoAlumnoAdapter mAdapter;
    private List<GrupoAlumnoDTO> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_alumno);
        mRecyclerView=findViewById(R.id.recyclerGrupoAlumno);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initList();
        refreshLayout=findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initList();
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }
            }

        });




    }

    private void initList(){
        int codigoGrupo=getIntent().getIntExtra("grupo",-1);
        String url="http://192.168.0.14:9090/Lab1/getAlumnos?grupo="+codigoGrupo;
        NetworkConnection connection= new NetworkConnection(url, new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                try{
                    List<GrupoAlumnoDTO> aux= new ArrayList<>();
                    JSONArray array= new JSONArray(output);
                    for(int i=0; i<array.length(); i++){
                        GrupoAlumnoDTO grupoAl= new GrupoAlumnoDTO();
                        grupoAl.setGrupo(array.getJSONObject(i).getInt("grupo"));
                        grupoAl.setNota((float)array.getJSONObject(i).getDouble("nota"));
                        JSONObject alJson= array.getJSONObject(i).getJSONObject("alumno");
                        AlumnoDTO al= new AlumnoDTO();
                        al.setCedula(alJson.getString("cedula"));
                        al.setNombre(alJson.getString("nombre"));
                        grupoAl.setAlumno(al);
                        aux.add(grupoAl);

                    }
                    mAdapter=new GrupoAlumnoAdapter(aux,GrupoAlumnoActivity.this,GrupoAlumnoActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        connection.execute(NetworkConnection.GET);
    }
    @Override
    public void onContactSelected(GrupoAlumnoDTO grupoAlumno) {
        Toast.makeText(getApplicationContext(), "Selected: " +grupoAlumno.getAlumno().getCedula(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        GrupoAlumnoDTO aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
        //send data to Edit Activity
        Intent intent = new Intent(this, EditNotaActivity.class);
        intent.putExtra("grupoAlumno", aux);
        mAdapter.notifyDataSetChanged(); //restart left swipe view
        startActivity(intent);
    }

    @Override
    public void onItemMove(int source, int target) {

    }

    @Override
    public void onGrupoAlumnoClick(GrupoAlumnoDTO clicked) {
        Intent intent = new Intent(this, EditNotaActivity.class);
        intent.putExtra("grupoAlumno", clicked);
        mAdapter.notifyDataSetChanged(); //restart left swipe view
        startActivity(intent);
        finish();
    }
}
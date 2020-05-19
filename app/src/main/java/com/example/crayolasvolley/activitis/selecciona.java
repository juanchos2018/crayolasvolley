package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.MultiSelectAdapter;
import com.example.crayolasvolley.clases.ClsAlumnos;

import java.util.ArrayList;

public class selecciona extends AppCompatActivity implements MultiSelectAdapter.CallbackManager{


    ArrayList<ClsAlumnos> listaalumnos;
    private MultiSelectAdapter adapter;
    RecyclerView recyclerView;
    private boolean isAllSelectedFromAdapter;

    CheckBox cbSelectAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaalumnos=new ArrayList<>();

        setContentView(R.layout.activity_selecciona);
cbSelectAll=(CheckBox)findViewById(R.id.cb_select_all);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        adapter = new MultiSelectAdapter(listaalumnos,this, this);
        recyclerView.setItemAnimator(null);
        cargalista();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    //    adapter.notifyAdapter(CommonUtils.getPersonList());

        cbSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbSelectAll.isChecked()){
                    //adapter.selectAllItems();
                    Toast.makeText(selecciona.this, "Click", Toast.LENGTH_SHORT).show();
                    if (!isAllSelectedFromAdapter) adapter.selectAllItems();
                }
            }
        });

    }

    private  void cargalista(){

        ClsAlumnos o =new ClsAlumnos();
        o.setId_alumno(1);
        o.setNombre_alumno("juan");
        o.setApellido_alumno("rojas");
        o.setEdad_alumno("4");
        listaalumnos.add(o);

        ClsAlumnos o1 =new ClsAlumnos();
        o1.setId_alumno(2);
        o1.setNombre_alumno("pepe");
        o1.setApellido_alumno("mmani");
        o1.setEdad_alumno("4");
        listaalumnos.add(o1);

        ClsAlumnos o2 =new ClsAlumnos();
        o2.setId_alumno(3);
        o2.setNombre_alumno("kiaira");
        o2.setApellido_alumno("estela");
        o2.setEdad_alumno("4");
        listaalumnos.add(o2);


    }


    @Override
    public void onSelectOrDeSelectAll(boolean isAllSelected, boolean isFromAdapter) {
       // isAllSelectedFromAdapter = isFromAdapter;
//
       // if (isAllSelected) {
       //     cbSelectAll.setChecked(true);
       // } else {
       //     cbSelectAll.setChecked(false);
       // }
//
       // isAllSelectedFromAdapter = false;
    }
}

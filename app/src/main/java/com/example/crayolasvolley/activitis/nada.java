package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.AdapterAlumnos2;
import com.example.crayolasvolley.adapters.MultiSelectAdapter;
import com.example.crayolasvolley.clases.ClsAlumnos;
import com.example.crayolasvolley.clases.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

public class nada extends AppCompatActivity  implements MultiSelectAdapter.CallbackManager {

    JsonObjectRequest jsonObjectRequest;

    ArrayList<ClsAlumnos>listaalumnos;
    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;
    private boolean isAllSelectedFromAdapter;
    Button bt;
     CheckBox checkBox;
    private MultiSelectAdapter adaptermulti;
public static int globalposition;
    public  static int cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nada);

        listaalumnos=new ArrayList<>();
        bt=(Button)findViewById(R.id.btn1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoabrir();
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
    private void dialogoabrir() {
        listaalumnos.clear();
        cargalista();
     //   final ArrayList<ClsAlumnos> listaAlumnos=new ArrayList<>();
        builder1 = new AlertDialog.Builder(nada.this);
        builder1.setTitle("Todos los Alumnos");//

        Button btcerrrar,btnagregar,btntodo;

        TextView tvcantidad;
        final RecyclerView recyclerView;
        View v = LayoutInflater.from(nada.this).inflate(R.layout.dialogo_alumnos, null);
        recyclerView=(RecyclerView)v.findViewById(R.id.recylalumnos1);
        btnagregar=(Button)v.findViewById(R.id.btnagregaralumno);
        btntodo=(Button)v.findViewById(R.id.btntodo);
        checkBox=(CheckBox)v.findViewById(R.id.cb_select_all);

        tvcantidad=(TextView)v.findViewById(R.id.idcantidad);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // final AdapterAlumnos2 adapter=new AdapterAlumnos2(listaalumnos);
        adaptermulti = new MultiSelectAdapter(listaalumnos,this, this);
        recyclerView.setAdapter(adaptermulti);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()){

                   if (!isAllSelectedFromAdapter) adaptermulti.selectAllItems();
                }
                Toast.makeText(nada.this, "selecciono todo", Toast.LENGTH_SHORT).show();
            }
        });
        builder1.setView(v);
        alert  = builder1.create();
        alert.show();
    }

    public  void contar(){

        Toast.makeText(nada.this, "hola", Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, String.valueOf(contador), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSelectOrDeSelectAll(boolean isAllSelected, boolean isFromAdapter) {
        isAllSelectedFromAdapter = isFromAdapter;

        if (isAllSelected) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        isAllSelectedFromAdapter = false;
    }
}

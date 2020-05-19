package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.AdapterAlumnos;
import com.example.crayolasvolley.adapters.AdapterAlumnos2;
import com.example.crayolasvolley.clases.ClsAlumnos;
import com.example.crayolasvolley.clases.ClsSedes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlumnosSalon extends AppCompatActivity {

    StringRequest stringRequest;
    int idsalon;
    ProgressDialog progreso;
    RecyclerView recyclerAlumnosSalon;
    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;
    JsonObjectRequest jsonObjectRequest;

    public static int cantidadselseccionados=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_salon);

        idsalon=getIntent().getIntExtra("idsalon",0);

        FloatingActionButton fab = findViewById(R.id.fa6);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoabrir();
            }
        });

        //listaSedes=new ArrayList<>();
        recyclerAlumnosSalon= (RecyclerView) findViewById(R.id.recycleralumnossalon);
        recyclerAlumnosSalon.setLayoutManager(new LinearLayoutManager(this));

        cargarWebService();
    }

    private void cargarWebService() {


    }

    private void dialogoabrir() {
        final ArrayList<ClsAlumnos> listaAlumnos=new ArrayList<>();
        builder1 = new AlertDialog.Builder(AlumnosSalon.this);
        builder1.setTitle("Todos los Alumnos");//

        Button btcerrrar,btnagregar;
        TextView tvcantidad;
        final RecyclerView recyclerView;
        View v = LayoutInflater.from(AlumnosSalon.this).inflate(R.layout.dialogo_alumnos, null);
        recyclerView=(RecyclerView)v.findViewById(R.id.recylalumnos1);
        btnagregar=(Button)v.findViewById(R.id.btnagregaralumno);
        tvcantidad=(TextView)v.findViewById(R.id.idcantidad);

        tvcantidad.setText(""+cantidadselseccionados);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String ip=getString(R.string.ip);
        String url=ip+"/listaralumnos.php";
        RequestQueue queue = Volley.newRequestQueue(AlumnosSalon.this);

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ClsAlumnos alumno=null;
                JSONArray json=response.optJSONArray("alumnos");
                try {

                    for (int i=0;i<json.length();i++){
                        alumno=new ClsAlumnos();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        alumno.setId_alumno(jsonObject.optInt("id_alumno"));
                        alumno.setNombre_alumno(jsonObject.optString("nombre_alumno"));
                        alumno.setApellido_alumno(jsonObject.optString("apellido_alumno"));
                        listaAlumnos.add(alumno);
                    }
                    final AdapterAlumnos2 adapter=new AdapterAlumnos2(listaAlumnos);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int cantidad=adapter.selecciondaos();
                            Toast.makeText(AlumnosSalon.this, String.valueOf(cantidad), Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(AlumnosSalon.this, "No se ha podido establecer conexión con el servidor" +
                            " "+response, Toast.LENGTH_SHORT).show();
                  //  progress.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlumnosSalon.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int contador=0;
                for (ClsAlumnos e : listaAlumnos) {
                    if (e.isSelected()){
                        contador++;
                    }
                }
                Toast.makeText(AlumnosSalon.this, String.valueOf(contador), Toast.LENGTH_SHORT).show();
            }
        });
        builder1.setView(v);
        alert  = builder1.create();
        alert.show();
    }
}

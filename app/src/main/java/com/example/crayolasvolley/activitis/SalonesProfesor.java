package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.AdaptadorProfesorSalon;
import com.example.crayolasvolley.adapters.AdapterSalones;
import com.example.crayolasvolley.adapters.AdapterSedes;
import com.example.crayolasvolley.adapters.AdapterSpinner;
import com.example.crayolasvolley.adapters.SpinAdapter2;
import com.example.crayolasvolley.clases.ClsProfesorSalon;
import com.example.crayolasvolley.clases.ClsSalones;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.SpinAdapter;
import com.example.crayolasvolley.clases.VolleySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class SalonesProfesor extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {


    RecyclerView recyclerSalonesprofesor;
    ArrayList<String> listaSedes;

    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    android.app.AlertDialog.Builder builder1;
    AlertDialog alert;


    private SpinAdapter adapter;
    private SpinAdapter2 adapter2;
    StringRequest stringRequest;
    int idprofesor;
    ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salones_profesor);

        idprofesor=getIntent().getIntExtra("idprofe",0);
        FloatingActionButton fab = findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoabrir();
            }
        });

        recyclerSalonesprofesor= (RecyclerView) findViewById(R.id.recyclcersolesprofesor);
        recyclerSalonesprofesor.setLayoutManager(new LinearLayoutManager(this));
        cargarWebService();
    }
    Spinner spinner;
    private void dialogoabrir() {

        final ArrayList<ClsSedes> listaSedes2=new ArrayList<>();
        ClsSedes sedes;
        builder1 = new AlertDialog.Builder(SalonesProfesor.this);
        Button btcerrrar,btnagregar;
        final RecyclerView recyclerView;

        final TextView tvidsalon;
        View v = LayoutInflater.from(SalonesProfesor.this).inflate(R.layout.dialogo_salones, null);
        recyclerView=(RecyclerView)v.findViewById(R.id.recylcersedes1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String ip=getString(R.string.ip);
        String url=ip+"/listasedes.php";
        RequestQueue queue = Volley.newRequestQueue(SalonesProfesor.this);
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ClsSedes sedes=null;
                JSONArray json=response.optJSONArray("sedes");
                try {

                    for (int i=0;i<json.length();i++){
                        sedes=new ClsSedes();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        sedes.setId_sede(jsonObject.optInt("id_sede"));
                        sedes.setNombre_sede(jsonObject.optString("nombre_sede"));
                        // sedes.setTelefono_sede(jsonObject.optString("telefono_sede"));
                    //    ids[contador]=sedes.getId_sede();
                  //      listaSedes1.add( sedes.getId_sede()+"-"+sedes.getNombre_sede());
                      //  listaSedes2.add(sedes);
                        listaSedes2.add(sedes);
                       // arratsedes[contador]=sedes;
                     //   contador++;
                    }

             //       ArrayAdapter<String> a=new ArrayAdapter<String >(SalonesProfesor.this,android.R.layout.simple_dropdown_item_1line,listaSedes1);
               //     spinner.setAdapter(a);
                 //   AdapterSpinner  adapter=new AdapterSpinner(listaSedes2);
                   // spinner.setAdapter(adapter);
                    //  AdapterSedes adapter=new AdapterSedes(listaSedes);

                //    adapter = new SpinAdapter(SalonesProfesor.this, android.R.layout.simple_spinner_item,listaSedes2);
                    adapter2 = new SpinAdapter2(SalonesProfesor.this, android.R.layout.simple_spinner_item,listaSedes2);
                    spinner.setAdapter(adapter2);

                } catch (JSONException e) {
                    e.printStackTrace();
                    makeText(SalonesProfesor.this, "No se ha podido establecer conexión con el servidor" +
                            " "+response, LENGTH_LONG).show();
                    progress.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    //    VolleySingleton.getIntanciaVolley(SalonesProfesor.this).addToRequestQueue(jsonObjectRequest);
       queue.add(jsonObjectRequest);
        builder1.setView(v);
        btcerrrar=(Button)v.findViewById(R.id.btncerrar);
        tvidsalon=(TextView)v.findViewById(R.id.idsalon);
        btnagregar=(Button)v.findViewById(R.id.btnagregar);
        spinner =(Spinner)v.findViewById(R.id.spinersede1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, final long id) {
            //    makeText(SalonesProfesor.this, spinner.getSelectedItemPosition(), LENGTH_SHORT).show();gStrinf
                ClsSedes user = adapter2.getItem(position);
                int idsede=user.getId_sede();
                String url2=ip+"/buscarsedes.php?id_sede="+idsede;
                RequestQueue queue = Volley.newRequestQueue(SalonesProfesor.this);
                jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ClsSalones sedes=null;
                         final ArrayList<ClsSalones> listaSedes3=new ArrayList<>();
                        JSONArray json=response.optJSONArray("sedes");
                        try {

                            for (int i=0;i<json.length();i++){
                                sedes=new ClsSalones();
                                JSONObject jsonObject=null;
                                jsonObject=json.getJSONObject(i);
                                sedes.setId_sede(jsonObject.optInt("id_sede"));
                                sedes.setId_salon(jsonObject.optInt("id_salon"));
                             //   Log.e("salones ",sedes.getNombre_salon());
                                sedes.setNombre_salon(jsonObject.optString("nombre_salon"));
                                  sedes.setCorreo_salon(jsonObject.optString("correo_salon"));
                                listaSedes3.add(sedes);
                            }

                            AdapterSalones adaptera=new AdapterSalones(listaSedes3);
                           recyclerView.setAdapter(adaptera);
                           adaptera.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                 //  int cantidad=listaSedes3.size();
                                  // makeText(SalonesProfesor.this, String.valueOf(cantidad), LENGTH_SHORT).show();
                                   //aqui algo
                                  // idsalon=listaSedes3.get(recyclerView.getChildAdapterPosition(v)).getId_salon();
                                   tvidsalon.setText(String.valueOf( listaSedes3.get(recyclerView.getChildAdapterPosition(v)).getId_salon()));
                                //   makeText(SalonesProfesor.this, String.valueOf( listaSedes3.get(recyclerView.getChildAdapterPosition(v)).getId_salon()), LENGTH_SHORT).show();
                               }
                           });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            makeText(SalonesProfesor.this, "No se ha podido establecer conexión con el servidor" +
                                    " "+response, LENGTH_LONG).show();
                            //progress.hide();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
               queue.add(jsonObjectRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btcerrrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idsalon= Integer.parseInt(tvidsalon.getText().toString());
                if (idsalon==0){
                    makeText(SalonesProfesor.this, "No existe este salon", LENGTH_SHORT).show();
                }
                else{
                    agregarprofesorsalon(idsalon);
                }

                //    int cantidad=spinner.getSelectedItemPosition();
               // Toast.makeText( SalonesProfesor.this, String.valueOf(listaSedes.get(recyclerView.getChildAdapterPosition(v)).getId_sede()), Toast.LENGTH_SHORT).show();

            }
        });

        alert  = builder1.create();
     //   alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
    }

    private void agregarprofesorsalon(final int idsalon) {

        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        final String ip=getString(R.string.ip);
        final String url=ip+"/agregarprofesorsalon.php?";
        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progreso.hide();
                if (response.trim().equalsIgnoreCase("registra")){

                    Toast.makeText(SalonesProfesor.this,"Registrado Salon",Toast.LENGTH_SHORT).show();
                }
                else if(response.trim().equalsIgnoreCase("existe")){
                    makeText(SalonesProfesor.this, "ya existe", LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(SalonesProfesor.this,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SalonesProfesor.this,"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.hide();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("id_profesor",String.valueOf( idprofesor));
                parametros.put("id_salon",String.valueOf(idsalon));
                parametros.put("fecha_creacion","17/05/2020");
                return parametros;
            }
        };
        //request.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(SalonesProfesor.this).addToRequestQueue(stringRequest);
    }


    private void cargarWebService() {

        progress=new ProgressDialog(this);
        progress.setMessage("Consultando...");
        progress.show();

        String ip=getString(R.string.ip);

       // String url=ip+"/listasedes.php";
        String url2=ip+"/listaprofesorsalon.php?id_profesor="+idprofesor;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url2,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(SalonesProfesor.this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        makeText(SalonesProfesor.this, "No se puede conectar "+error.toString(), LENGTH_LONG).show();
        System.out.println();

        progress.hide();
    }

    @Override
    public void onResponse(JSONObject response) {
        //AdaptadorProfesorSalon
        ArrayList<ClsProfesorSalon> lista=new ArrayList<>();
        ClsProfesorSalon sedes=null;
        JSONArray json=response.optJSONArray("salones");
        try {

            for (int i=0;i<json.length();i++){
                sedes=new ClsProfesorSalon();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                sedes.setNombre_salon(jsonObject.optString("nombre_salon"));
                sedes.setCorre_salon(jsonObject.optString("correo_salon"));
                sedes.setNombre_sede(jsonObject.optString("nombre_sede"));

                lista.add(sedes);
            }
            progress.hide();
           // ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,listaSedes);
            AdaptadorProfesorSalon adapter= new AdaptadorProfesorSalon(lista);
            recyclerSalonesprofesor.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            makeText(SalonesProfesor.this, "No se ha podido establecer conexión con el servidor" +
                    " "+response, LENGTH_LONG).show();
            progress.hide();
        }

    }


}

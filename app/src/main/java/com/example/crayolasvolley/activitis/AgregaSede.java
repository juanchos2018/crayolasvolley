package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class AgregaSede extends AppCompatActivity {

    EditText et_nombresede,et_direccion_sede,et_telefonosede;
    Button btn_registrar,btnsesdes;
    ProgressDialog progreso;

    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega_sede);
        et_nombresede=(EditText)findViewById(R.id.et_sede);
        et_direccion_sede=(EditText)findViewById(R.id.et_direccion);
        et_telefonosede=(EditText)findViewById(R.id.et_telefono);
        btn_registrar=(Button)findViewById(R.id.btn_registrar);
        btnsesdes=(Button)findViewById(R.id.btnsedes);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sede=et_nombresede.getText().toString();
                String direccion=et_direccion_sede.getText().toString();
                String telefono=et_telefonosede.getText().toString();
                registrarsede(sede,direccion,telefono);
            }
        });
        btnsesdes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsitasedes();
            }
        });
    }

    private void lsitasedes() {

        startActivity(new Intent(AgregaSede.this,ListaSedes.class));
    }

    private void registrarsede(final String sede, final String direccion, final String telefono) {

        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        final String ip=getString(R.string.ip);
        ///public_html/agregasede2.php
    //    String url=ip+"/agregasede2.php?";
        final String url=ip+"/agregasede3.php?";
      //  final String url=ip+"/ejemploBDRemota/wsJSONRegistroMovil.php?";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progreso.hide();

                if (response.trim().equalsIgnoreCase("registra")){

                    Toast.makeText(AgregaSede.this,"Se ha registrado con exito",Toast.LENGTH_SHORT).show();
                }
                else  if (response.trim().equalsIgnoreCase("existe")){
                    Toast.makeText(AgregaSede.this, "ya existe esta sede", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AgregaSede.this,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AgregaSede.this,"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("nombre_sede",sede);
                parametros.put("direccion_sede",direccion);
                parametros.put("telefono_sede",telefono);

                return parametros;
            }
        };
        //request.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(AgregaSede.this).addToRequestQueue(stringRequest);
    }
}

package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

public class AgregarProfesor extends AppCompatActivity {

    EditText et_nombre,et_apellido,et_correo,et_telefon,et_direccion;
    Button btnregistrar;
    JsonObjectRequest jsonObjectRequest;
    StringRequest stringRequest;
    ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_profesor);

        et_nombre=(EditText)findViewById(R.id.idnombresprefesor);
        et_apellido=(EditText)findViewById(R.id.idapellidoprofesor);
        et_correo=(EditText)findViewById(R.id.idedcorreo);
        et_telefon=(EditText)findViewById(R.id.idteleofono);
        et_direccion=(EditText)findViewById(R.id.iddireccion);
        btnregistrar=(Button)findViewById(R.id.idbotonregistaprofe);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=et_nombre.getText().toString();
                String apellido=et_apellido.getText().toString();
                String correo=et_correo.getText().toString();
                String telefono=et_telefon.getText().toString();
                String direccion=et_direccion.getText().toString();
                String foto="rutafot";
                registrarprofe(nombre,apellido,correo,telefono,direccion,foto);
            }
        });

    }

    private void registrarprofe(final String nombre, final String apellido, final String correo, final String telefono, final String direccion, final String foto) {

        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        final String ip=getString(R.string.ip);
        final String url=ip+"/agregarprofesor.php?";


        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progreso.hide();

                if (response.trim().equalsIgnoreCase("registra")){

                    Toast.makeText(AgregarProfesor.this,"Registrado Profesor",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(AgregarProfesor.this,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AgregarProfesor.this,"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("nombre_profesor",nombre);
                parametros.put("apellido_profesor",apellido);
                parametros.put("correo_profesor",correo);
                parametros.put("telefono_profesor",telefono);
                parametros.put("direccion_profesor",direccion);
                parametros.put("foto_profesor",foto);

                return parametros;
            }
        };
        //request.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(AgregarProfesor.this).addToRequestQueue(stringRequest);
    }

}

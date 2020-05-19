package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class AgregarAlumno extends AppCompatActivity {


    ToggleButton toggleButton, toggleButton2;
    EditText etnnombrepoderado2, etcorreoapoderado2;
    EditText etnombreaalumno,etapellidoalumno,etedadalumno,etnombreapoderado1,etcorreoapoderado1,ettelefono;
    Button btnregistraralumno;
    StringRequest stringRequest;
    ProgressDialog progreso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumno);

        toggleButton=(ToggleButton)findViewById(R.id.idtoglebutton);
        toggleButton2=(ToggleButton)findViewById(R.id.idtoglebutton2);

        etnombreaalumno=(EditText)findViewById(R.id.idnombresalumno);
        etapellidoalumno=(EditText)findViewById(R.id.idapellidosalumno);
        etedadalumno=(EditText)findViewById(R.id.idedadalumno);
        etnombreapoderado1=(EditText)findViewById(R.id.idnombrepoderado1);
        etcorreoapoderado1=(EditText)findViewById(R.id.idcorreoapoderado1);
        etnnombrepoderado2=(EditText)findViewById(R.id.idnombreapoderado2);
        etcorreoapoderado2=(EditText)findViewById(R.id.idcorreoapoderado2);
        ettelefono=(EditText)findViewById(R.id.idteleofonoapoderado);
        btnregistraralumno=(Button)findViewById(R.id.idbotonregistraralumno);


        etnnombrepoderado2.setVisibility(View.GONE);
        etcorreoapoderado2.setVisibility(View.GONE);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togle1();
            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togle2();
            }
        });

        btnregistraralumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomnbrealumno=etnombreaalumno.getText().toString();
                String apellidoalumno=etapellidoalumno.getText().toString();
                String edadalumno=etedadalumno.getText().toString().trim();
                String nombreapodrado1=etnombreapoderado1.getText().toString();
                String correoapoderado1=etcorreoapoderado1.getText().toString().trim();
                String telefono=ettelefono.getText().toString().trim();

                String nombreapoderado2=etnnombrepoderado2.getText().toString();
                String correoapoderad2=etcorreoapoderado2.getText().toString().trim();
              //  String tokentareas=generartoken(telefono);

                registraralumno(nomnbrealumno,apellidoalumno,edadalumno,nombreapodrado1,correoapoderado1,nombreapoderado2,correoapoderad2,telefono);
            }
        });
    }

    private void registraralumno(final String nomnbrealumno, final String apellidoalumno, final String edadalumno, final String nombreapodrado1, final String correoapoderado1, final String nombreapoderado2, final String correoapoderad2, final String telefono) {

        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        final String ip=getString(R.string.ip);
        final String url=ip+"/agregaralumnos.php?";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progreso.hide();

                if (response.trim().equalsIgnoreCase("registra")){

                    Toast.makeText(AgregarAlumno.this,"Registrado Alumno",Toast.LENGTH_SHORT).show();
                }

                else if (response.trim().equalsIgnoreCase("existe")){
                    Toast.makeText(AgregarAlumno.this, "Existe", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AgregarAlumno.this,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AgregarAlumno.this,"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("nombre_alumno",nomnbrealumno);
                parametros.put("apellido_alumno",apellidoalumno);
                parametros.put("edad_alumno",edadalumno);
                parametros.put("nombre_apoderado1",nombreapodrado1);
                parametros.put("nombre_apoderado2",nombreapoderado2);
                parametros.put("correo_apoderado1",correoapoderado1);
                parametros.put("correo_apoderado2",correoapoderad2);
                parametros.put("telefono_alumno",telefono);
                parametros.put("rutafoto_alumno","ruta/foto");

                return parametros;
            }
        };
        //request.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(AgregarAlumno.this).addToRequestQueue(stringRequest);

    }

    private void togle1(){
        if (toggleButton.isChecked()){

            etnnombrepoderado2.setVisibility(View.VISIBLE);
        }
        else{
            etnnombrepoderado2.setVisibility(View.GONE);

        }
    }
    private void togle2(){
        if (toggleButton2.isChecked()){
            etcorreoapoderado2.setVisibility(View.VISIBLE);
        }
        else{
            etcorreoapoderado2.setVisibility(View.GONE);
        }
    }
}

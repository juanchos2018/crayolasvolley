package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.crayolasvolley.MainActivity;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.Usuario;
import com.example.crayolasvolley.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextView copyrightTV1,etvolvidclave;
    Button btnlogin;
    EditText etcorreo,etclave;
    CheckBox checkBox;
    private ProgressDialog progressDialog;
    String tipousua;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog dialog;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etcorreo=(EditText)findViewById(R.id.inputEmail);
        etclave=(EditText)findViewById(R.id.inputPassword);
        checkBox=(CheckBox)findViewById(R.id.idcheckorecordar);
        etvolvidclave=(TextView)findViewById(R.id.linkForgotPassword);
        copyrightTV1 = findViewById(R.id.copyrightTV);
        btnlogin=(Button)findViewById(R.id.loginButton);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo=etcorreo.getText().toString().trim();
                String clave=etclave.getText().toString().trim();

                Ingresar(correo,clave);
            }
        });



    }

    private void Ingresar(String correo, String clave) {

        final String ip=getString(R.string.ip);
        String url=ip+"/login3.php?correo="+ correo+"&clave="+clave;
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.hide();

                Usuario miUsuario=new Usuario();

                JSONArray json=response.optJSONArray("usuario");
                JSONObject jsonObject=null;

                try {
                    jsonObject=json.getJSONObject(0);
                    miUsuario.setNombre(jsonObject.optString("nombre"));
                    miUsuario.setProfesion(jsonObject.optString("profesion"));
                    miUsuario.setTiposuario(jsonObject.optString("tipo_usu"));

                    if (miUsuario.getTiposuario().equals("Admin")){
                        Intent intent= new Intent(Login.this, MainActivity.class);
                        startActivity(intent);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                pDialog.hide();
                Log.d("ERROR: ", error.toString());
            }
        });

        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getBaseContext()).addToRequestQueue(jsonObjectRequest);

    }
}

package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.VolleySingleton;

import org.json.JSONObject;

public class nada extends AppCompatActivity {

    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nada);
    }

    private  void listar(){

        String ip=getString(R.string.ip);

        String url=ip+"/listasedes.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(nada.this).addToRequestQueue(jsonObjectRequest);

    }


}

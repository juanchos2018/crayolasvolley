package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.AdapterSedes;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.ListaView;
import com.example.crayolasvolley.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class AgregarSalon extends AppCompatActivity  implements Response.Listener<JSONObject>,Response.ErrorListener{

    EditText etnombre_salon,etcorreo_salon,etclave_salon;
    Spinner spiner_sede;
    Button btnregistrar;
    ListView listView;
    ProgressDialog progreso;

    ArrayAdapter<ClsSedes> adaperspinner;
    ArrayAdapter<String> adaperspinner2;
    public static ArrayList<String> listaSedes;
    ArrayList<ClsSedes> listaSedes2;

    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private AsyncHttpClient client;
    StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_salon);

        cargarcontroles();
        listaSedes=new ArrayList<String>();
        listaSedes2=new ArrayList<>();
        client=new AsyncHttpClient();

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=etnombre_salon.getText().toString();
                String correo=etcorreo_salon.getText().toString();
                String clave=etclave_salon.getText().toString();
                String sede=spiner_sede.getSelectedItem().toString();

                registrarsalon(nombre,correo,clave,sede);
            }
        });

        cargarWebService();
          //  cargarspiner();
    }

    private void registrarsalon(final String nombre, final String correo, final String clave, String sede) {
        int position =spiner_sede.getSelectedItemPosition();
        final int id_sede =ids[position];

        //agregarcorreosalon
        progreso=new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();
        final String ip=getString(R.string.ip);
        final String url=ip+"/agregarcorreosalon.php?";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progreso.hide();

                if (response.trim().equalsIgnoreCase("registra")){

                    Toast.makeText(AgregarSalon.this,"Se ha registrado con exito",Toast.LENGTH_SHORT).show();
                }
                else  if (response.trim().equalsIgnoreCase("existe")){
                    Toast.makeText(AgregarSalon.this, "ya existe esta sede", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AgregarSalon.this,"No se ha registrado ",Toast.LENGTH_SHORT).show();
                    Log.i("RESPUESTA: ",""+response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AgregarSalon.this,"No se ha podido conectar",Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros=new HashMap<>();
                parametros.put("nombre_salon",nombre);
                parametros.put("correo_salon",correo);
                parametros.put("clave_salon",clave);
                parametros.put("id_sede", String.valueOf(id_sede));

                return parametros;
            }
        };
        //request.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(AgregarSalon.this).addToRequestQueue(stringRequest);

    }

    private void cargarcontroles(){
        etnombre_salon=(EditText)findViewById(R.id.et_nombresalon);
        etcorreo_salon=(EditText)findViewById(R.id.et_correosalon);
        etclave_salon=(EditText)findViewById(R.id.et_clavesalon);
        spiner_sede=(Spinner)findViewById(R.id.spiner_sede);
        btnregistrar=(Button)findViewById(R.id.btn_registrarsede);
        listView=(ListView)findViewById(R.id.lisviue);
    }
    private void cargarspiner(){

        String ip=getString(R.string.ip);
        String url=ip+"/listasedes.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200){
                    llenarspinder(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void llenarspinder(String s) {

        ArrayList<ClsSedes> listasedesa =new ArrayList<ClsSedes>();
        try {

            JSONArray jsonArray=new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                ClsSedes o=new ClsSedes();
                o.setNombre_sede(jsonArray.getJSONObject(i).getString("nombre_sede"));
                listasedesa.add(o);
            }

            ArrayAdapter<ClsSedes> a=new ArrayAdapter<ClsSedes>(this,android.R.layout.simple_dropdown_item_1line,listasedesa);
            spiner_sede.setAdapter(a);

        }catch (Exception ex){
            Toast.makeText(this, "Er " +ex.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("er" ,ex.getMessage());
        }
    }

    private void cargarWebService() {

        progress=new ProgressDialog(this);
        progress.setMessage("Consultando...");
        progress.show();

        String ip=getString(R.string.ip);
        String url=ip+"/listasedes.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(AgregarSalon.this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(AgregarSalon.this, "No se puede conectar al servidor "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }
    int [] ids ;
    int contador=0;
    @Override
    public void onResponse(JSONObject response) {

        ids=new int[100];

        ArrayList<ClsSedes> lista =new ArrayList<ClsSedes>();
        ArrayList<String> lis=new ArrayList<>();
     //   JSONArray json=response.optJSONArray("sedes");
        try {

           // for (int i=0;i<json.length();i++){
          //      ClsSedes    o=new ClsSedes();
           //     JSONObject jsonObject=null;
              //  jsonObject=json.getJSONObject(i);
             //   sedes.setId_sede(jsonObject.optInt("id_sede"));
              //  o.setNombre_sede(jsonObject.optString("nombre_sede"));
            //       o.setNombre_sede(json.getJSONObject(i).getString("nombre_sede"));
          //      lista.add(o);
                // sedes.setTelefono_sede(jsonObject.optString("telefono_sede"));
             //   listaSedes2.add(sedes);
             //   ids[contador]=o.getId_sede();
               // contador++;
                //listaSedes.add(o.getNombre_sede());
       //     }
            JSONArray jsonArray=response.optJSONArray("sedes");
            for (int i=0;i<jsonArray.length();i++){
                ClsSedes o=new ClsSedes();
                o.setNombre_sede(jsonArray.getJSONObject(i).getString("nombre_sede"));
                o.setId_sede(jsonArray.getJSONObject(i).getInt("id_sede"));
                lis.add(o.getNombre_sede());
                ids[contador]=o.getId_sede();
                 contador++;
                //lista.add(o);
            }



            progress.hide();
            ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,lis);
            spiner_sede.setAdapter(a);
          //  AdapterSedes adapter=new AdapterSedes(listaSedes2);

          //  adaperspinner2= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lista);
            //spiner_sede.setAdapter(adaperspinner2);
          // ArrayAdapter<ClsSedes> a=new ArrayAdapter<ClsSedes>(this,android.R.layout.simple_dropdown_item_1line,lista);
         //   ArrayAdapter<ClsSedes> a=new ArrayAdapter<ClsSedes>(this,android.R.layout.test_list_item,lista);
           // listView.setAdapter(a);

         ///   ListaView trackListAdapter = new ListaView(AgregarSalon.this, lista);
         //   spiner_sede.setAdapter(trackListAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(AgregarSalon.this, "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
}

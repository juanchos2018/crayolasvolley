package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.AdapterSedes;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaSedes extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    RecyclerView recyclerSedes;
    ArrayList<ClsSedes> listaSedes;

    ProgressDialog progress;

    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sedes);
        listaSedes=new ArrayList<>();
        recyclerSedes= (RecyclerView) findViewById(R.id.recylcersedes);
        recyclerSedes.setLayoutManager(new LinearLayoutManager(this));
        cargarWebService();

    }


    private void cargarWebService() {

        progress=new ProgressDialog(this);
        progress.setMessage("Consultando...");
        progress.show();

            String ip=getString(R.string.ip);

            String url=ip+"/listasedes.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(ListaSedes.this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(ListaSedes.this, "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
        System.out.println();
        Log.d("ERROR: ", error.toString());
        progress.hide();
    }

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
                listaSedes.add(sedes);
            }
            progress.hide();
            AdapterSedes adapter=new AdapterSedes(listaSedes);
            recyclerSedes.setAdapter(adapter);
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  Toast.makeText( ListaSedes.this, String.valueOf(listaSedes.get(recyclerSedes.getChildAdapterPosition(v)).getId_sede()), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(ListaSedes.this, "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }
}

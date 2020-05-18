package com.example.crayolasvolley.ui.sede;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.crayolasvolley.R;
import com.example.crayolasvolley.activitis.AgregaSede;
import com.example.crayolasvolley.activitis.ListaSedes;
import com.example.crayolasvolley.adapters.AdapterSedes;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.VolleySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SedesFragment extends Fragment  implements Response.Listener<JSONObject>,Response.ErrorListener{

    private SedesViewModel mViewModel;

    public static SedesFragment newInstance() {
        return new SedesFragment();
    }

    RecyclerView recyclerSedes;
    ArrayList<ClsSedes> listaSedes;

    ProgressDialog progress;

    JsonObjectRequest jsonObjectRequest;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.sedes_fragment, container, false);

        FloatingActionButton fab = vista.findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarsede();
            }
        });
        listaSedes=new ArrayList<>();
        recyclerSedes= (RecyclerView) vista.findViewById(R.id.recylcersedes1);
        recyclerSedes.setLayoutManager(new LinearLayoutManager(getContext()));
        cargarWebService();
        return vista;
    }

    private void agregarsede() {
        startActivity(new Intent(getContext(), AgregaSede.class));
    }

    private void cargarWebService() {

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String ip=getString(R.string.ip);

        String url=ip+"/listasedes.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SedesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
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
                sedes.setTelefono_sede(jsonObject.optString("telefono_sede"));
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
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }

    }
}

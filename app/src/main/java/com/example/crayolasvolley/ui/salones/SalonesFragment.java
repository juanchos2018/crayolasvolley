package com.example.crayolasvolley.ui.salones;

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
import com.example.crayolasvolley.activitis.AgregarSalon;
import com.example.crayolasvolley.activitis.ListaSedes;
import com.example.crayolasvolley.adapters.AdapterSalones;
import com.example.crayolasvolley.adapters.AdapterSedes;
import com.example.crayolasvolley.clases.ClsSalones;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.VolleySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SalonesFragment extends Fragment  implements Response.Listener<JSONObject>,Response.ErrorListener{

    private SalonesViewModel mViewModel;

    public static SalonesFragment newInstance() {
        return new SalonesFragment();
    }


    RecyclerView recyclerSalones;
    ArrayList<ClsSalones> listaSalones;

    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista= inflater.inflate(R.layout.salones_fragment, container, false);
        FloatingActionButton fab = vista.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarsalon();
            }
        });

        listaSalones=new ArrayList<>();
        recyclerSalones= (RecyclerView)vista.findViewById(R.id.recylcermsalones);
        recyclerSalones.setLayoutManager(new LinearLayoutManager(getContext()));
        cargarWebService();

        return vista;
    }

    private void cargarWebService() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();
//startActivity(new Intent(Login.this,AsignarSalon.class));
        String ip=getString(R.string.ip);

        String url=ip+"/listarsalones.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);

    }

    private void agregarsalon() {
        startActivity(new Intent(getContext(), AgregarSalon.class));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonesViewModel.class);
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
        ClsSalones salon=null;
        JSONArray json=response.optJSONArray("salones");
        try {

            for (int i=0;i<json.length();i++){
                salon=new ClsSalones();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

             //   salon.setId_sede(jsonObject.optInt("id_sede"));
                salon.setNombre_salon(jsonObject.optString("nombre_salon"));
                salon.setCorreo_salon(jsonObject.optString("correo_salon"));
                salon.setSede_salon(jsonObject.optString("nombre_sede"));

                listaSalones.add(salon);
            }
            progress.hide();
            AdapterSalones adapter=new AdapterSalones(listaSalones);
            recyclerSalones.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
}

package com.example.crayolasvolley.ui.profesores;

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
import com.example.crayolasvolley.activitis.AgregarProfesor;
import com.example.crayolasvolley.activitis.ListaSedes;
import com.example.crayolasvolley.adapters.AdapterProfesores;
import com.example.crayolasvolley.adapters.AdapterSedes;
import com.example.crayolasvolley.clases.ClsProfesores;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.VolleySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfesoresFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

    private ProfesoresViewModel mViewModel;

    RecyclerView recyclerProfesores;
    ArrayList<ClsProfesores> listaProfesores;
    ProgressDialog progress;

    JsonObjectRequest jsonObjectRequest;
    public static ProfesoresFragment newInstance() {
        return new ProfesoresFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.profesores_fragment, container, false);

        FloatingActionButton fab = vista.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //   .setAction("Action", null).show();
                agregarprofesor();
            }
        });
        listaProfesores=new ArrayList<>();
        recyclerProfesores= (RecyclerView) vista.findViewById(R.id.recyclerprofesores);
        recyclerProfesores.setLayoutManager(new LinearLayoutManager(getContext()));

        cargarWebService();


        return vista;
    }

    private void agregarprofesor() {

        startActivity(new Intent(getContext(), AgregarProfesor.class));
    }

    private void cargarWebService() {

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String ip=getString(R.string.ip);

        String url=ip+"/listaprofesores.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfesoresViewModel.class);

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
        ClsProfesores profesor=null;
        JSONArray json=response.optJSONArray("profesores");
        try {

            for (int i=0;i<json.length();i++){
                profesor=new ClsProfesores();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                profesor.setId_profesor(jsonObject.optInt("id_profesor"));
                Log.e("idpro ", String.valueOf(profesor.getId_profesor()));
                profesor.setNombre_profesor(jsonObject.optString("nombre_profesor"));
                profesor.setApellido_profesor(jsonObject.optString("apellido_profesor"));
                profesor.setCorreo_profesor(jsonObject.optString("correo_profesor"));

                listaProfesores.add(profesor);
            }
            progress.hide();
            AdapterProfesores adapter=new AdapterProfesores(listaProfesores);
            recyclerProfesores.setAdapter(adapter);
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

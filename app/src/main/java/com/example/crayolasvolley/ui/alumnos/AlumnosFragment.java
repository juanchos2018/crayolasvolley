package com.example.crayolasvolley.ui.alumnos;

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
import com.example.crayolasvolley.activitis.AgregarAlumno;
import com.example.crayolasvolley.adapters.AdapterAlumnos;
import com.example.crayolasvolley.clases.ClsAlumnos;
import com.example.crayolasvolley.clases.ClsProfesores;
import com.example.crayolasvolley.clases.VolleySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlumnosFragment extends Fragment  implements Response.Listener<JSONObject>,Response.ErrorListener{

    private AlumnosViewModel mViewModel;

    RecyclerView recyclerAlumnos;
    ArrayList<ClsAlumnos> listaAlumnos;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    public static AlumnosFragment newInstance() {
        return new AlumnosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.alumnos_fragment, container, false);

        FloatingActionButton fab = vista.findViewById(R.id.fa5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregaralumno();
            }
        });

        listaAlumnos=new ArrayList<>();
        recyclerAlumnos= (RecyclerView) vista.findViewById(R.id.recycleralumnos);
        recyclerAlumnos.setLayoutManager(new LinearLayoutManager(getContext()));

        cargarWebService();
        return vista;
    }

    private void cargarWebService() {
        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String ip=getString(R.string.ip);

        String url=ip+"/listaralumnos.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void agregaralumno() {

        startActivity(new Intent(getContext(), AgregarAlumno.class));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AlumnosViewModel.class);
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
        ClsAlumnos alumno=null;
        JSONArray json=response.optJSONArray("alumnos");
        try {

            for (int i=0;i<json.length();i++){
                alumno=new ClsAlumnos();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                alumno.setId_alumno(jsonObject.optInt("id_alumno"));
                alumno.setNombre_alumno(jsonObject.optString("nombre_alumno"));
                alumno.setApellido_alumno(jsonObject.optString("apellido_alumno"));
                alumno.setEdad_alumno(jsonObject.optString("edad_alumno"));

                listaAlumnos.add(alumno);
            }
            progress.hide();
            AdapterAlumnos adapter=new AdapterAlumnos(listaAlumnos);
            recyclerAlumnos.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progress.hide();
        }
    }
}

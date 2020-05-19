package com.example.crayolasvolley.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.ClsAlumnos;

import java.util.ArrayList;

public class AdapterAlumnos  extends RecyclerView.Adapter<AdapterAlumnos.ViewHolderDatos> implements View.OnClickListener {


    ArrayList<ClsAlumnos> listaAlumnos;

    public AdapterAlumnos(ArrayList<ClsAlumnos> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumnos,parent,false);
        vista.setOnClickListener(this);
        return new  ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos){
            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.txtnombres.setText(listaAlumnos.get(position).getNombre_alumno());
            items.txtapellidos.setText(listaAlumnos.get(position).getApellido_alumno());
            items.txtedad.setText(listaAlumnos.get(position).getEdad_alumno());
           // items.id_profesor=listaProfesores.get(position).getId_profesor();

        }
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txtnombres,txttelefono,txtcorreo,txtapellidos,txtedad;
        ImageView imgfoto;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            txtnombres=(TextView)itemView.findViewById(R.id.idnmbresitems);
            txtapellidos=(TextView)itemView.findViewById(R.id.idapellidositems);
            txtedad=(TextView)itemView.findViewById(R.id.idedaditems);

            imgfoto=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}

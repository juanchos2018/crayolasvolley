package com.example.crayolasvolley.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.ClsProfesorSalon;

import java.util.ArrayList;

public class AdaptadorProfesorSalon extends RecyclerView.Adapter<AdaptadorProfesorSalon.ViewHolderDatos> implements View.OnClickListener {


    ArrayList<ClsProfesorSalon> listaprofesorsalon;

    public AdaptadorProfesorSalon(ArrayList<ClsProfesorSalon> listaprofesorsalon) {
        this.listaprofesorsalon = listaprofesorsalon;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profesor_salon,parent,false);
        vista.setOnClickListener(this);
        return new  ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos){
            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.tv_nombresalon.setText(listaprofesorsalon.get(position).getNombre_salon());
            items.tv_correosalon.setText(listaprofesorsalon.get(position).getCorre_salon());
            items.tv_nombresede.setText(listaprofesorsalon.get(position).getNombre_sede());


        }

    }

    @Override
    public int getItemCount() {
        return listaprofesorsalon.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tv_nombresalon,tv_correosalon,tv_nombresede;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            tv_nombresalon=(TextView)itemView.findViewById(R.id.idtvnombre_salon);
            tv_correosalon=(TextView)itemView.findViewById(R.id.idcorreo_salon);
            tv_nombresede=(TextView)itemView.findViewById(R.id.idnombre_sede);
        }
    }
}

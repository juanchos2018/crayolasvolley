package com.example.crayolasvolley.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.activitis.AsignarSalon;
import com.example.crayolasvolley.activitis.SalonesProfesor;
import com.example.crayolasvolley.clases.ClsProfesores;

import java.util.ArrayList;

public class AdapterProfesores  extends RecyclerView.Adapter<AdapterProfesores.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<ClsProfesores> listaProfesores;

    public AdapterProfesores(ArrayList<ClsProfesores> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    private View.OnClickListener listener;
    public  void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profesores,parent,false);
        vista.setOnClickListener(this);
        return new  ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos){
            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.tvnombre.setText(listaProfesores.get(position).getNombre_profesor());
            items.tvapellido.setText(listaProfesores.get(position).getApellido_profesor());
            items.tvcorreo.setText(listaProfesores.get(position).getCorreo_profesor());
            items.id_profesor=listaProfesores.get(position).getId_profesor();

        }

    }

    @Override
    public int getItemCount() {
        return listaProfesores.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
    TextView  tvnombre,tvapellido,tvcorreo;
    int id_profesor;
        final Toolbar toolbarCard = (Toolbar)itemView.findViewById(R.id.idtolbar1);
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            tvnombre=(TextView)itemView.findViewById(R.id.tv_nombreprofesor);
            tvapellido=(TextView)itemView.findViewById(R.id.tv_apellidorofesor);
            tvcorreo=(TextView)itemView.findViewById(R.id.tv_correooprofesor);
            toolbarCard.inflateMenu(R.menu.menu_profesor);
            toolbarCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_agregar:
                            Intent intent= new Intent(toolbarCard.getContext(), SalonesProfesor.class);// Ver alumnos
                            Bundle bundle= new Bundle();
                            bundle.putInt("idprofe",id_profesor);
                            intent.putExtras(bundle);
                            toolbarCard.getContext().startActivity(intent);
                            break;


                    }
                    return true;
                }
            });



        }
    }
}

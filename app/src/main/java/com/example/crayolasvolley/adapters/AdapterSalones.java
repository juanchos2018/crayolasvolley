package com.example.crayolasvolley.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.ClsSalones;

import java.util.ArrayList;

public class AdapterSalones   extends RecyclerView.Adapter<AdapterSalones.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<ClsSalones> listaSalones;

    public AdapterSalones(ArrayList<ClsSalones> listaSalones) {
        this.listaSalones = listaSalones;
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
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salones,parent,false);
        vista.setOnClickListener(this);
        return new  ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if (holder instanceof ViewHolderDatos){
            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.tvnombresalon.setText(listaSalones.get(position).getNombre_salon());
            items.tvcorreosalon.setText(listaSalones.get(position).getCorreo_salon());
            items.tvsedesalon.setText(listaSalones.get(position).getSede_salon());


        }
    }

    @Override
    public int getItemCount() {
        return listaSalones.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvnombresalon,tvcorreosalon,tvsedesalon;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvnombresalon=(TextView)itemView.findViewById(R.id.tv_nombresalon);
            tvcorreosalon=(TextView)itemView.findViewById(R.id.tv_correosalon);
            tvsedesalon=(TextView)itemView.findViewById(R.id.tv_sedeosalon);
        }
    }
}

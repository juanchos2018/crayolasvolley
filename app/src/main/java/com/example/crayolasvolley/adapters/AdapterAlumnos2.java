package com.example.crayolasvolley.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.activitis.AlumnosSalon;
import com.example.crayolasvolley.activitis.nada;
import com.example.crayolasvolley.clases.ClsAlumnos;
import com.example.crayolasvolley.clases.ClsSedes;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdapterAlumnos2  extends RecyclerView.Adapter<AdapterAlumnos2.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<ClsAlumnos> listaAlumnos;
    int cantador;
    private Set<Integer> selectedItemsList;
    Context context;
    private CallbackManager callbackManager;
    public AdapterAlumnos2(ArrayList<ClsAlumnos> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
        selectedItemsList = new HashSet<>();
    }

    private View.OnClickListener listener;
    public  void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }


    public interface CallbackManager {
        void onSelectOrDeSelectAll(boolean isAllSelected, boolean isFromAdapter);
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
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumnos2,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolderDatos(vista);
    }
int posicion;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDatos holder, int position) {

        if (holder instanceof ViewHolderDatos){
            final ClsAlumnos model = listaAlumnos.get(position);

            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.txtnombres.setText(listaAlumnos.get(position).getNombre_alumno());
            items.txtapellidos.setText(listaAlumnos.get(position).getApellido_alumno());
            items.txtedad.setText(listaAlumnos.get(position).getEdad_alumno());
           // posicion=postion;

            items.imgcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    model.setSelected(!model.isSelected());
                    items.imgcheck.setImageResource(model.isSelected()? R.drawable.ic_check_on:R.drawable.ic_check_of);
//
                    if (listaAlumnos.size() == selectedItemsList.size()) {
                        if (callbackManager != null) callbackManager.onSelectOrDeSelectAll(true, true);
                    } else {
                        if (callbackManager != null) callbackManager.onSelectOrDeSelectAll(false, true);
                    }
//
//
                }
            });


        }
    }

    public void  checktodo(){
        for (ClsAlumnos e :listaAlumnos){
          e.setSelected(true);

        }
       // listaAlumnos.notify();

    }

    public int selecciondaos(){
        int contador=0;
        for (ClsAlumnos e : listaAlumnos) {
            if (e.isSelected()){
                contador++;
            }
        }
        return  contador;
    }

    public void selectAllItems() {
        if (listaAlumnos == null) return;

        for (int i = 0, personItemListSize = listaAlumnos.size(); i < personItemListSize; i++) {
            ClsAlumnos item = listaAlumnos.get(i);

            if (!item.isSelected()) item.setSelected(true);
            if (!selectedItemsList.contains(item.getId_alumno())) {
                selectedItemsList.add(item.getId_alumno());
            }
        }
        notifyDataSetChanged();
    }

    public void getAdapterPosition(){
        if(posicion==nada.globalposition)
        {
            //change color like
   //         textview.setTextColor(Color.RED);
        }
        else
        {
            //revert back to regular color
///            textview.setTextColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txtnombres,txttelefono,txtcorreo,txtapellidos,txtedad;
        ImageView imgcheck;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtnombres=(TextView)itemView.findViewById(R.id.idnmbresitems1);
            txtapellidos=(TextView)itemView.findViewById(R.id.idapellidositems1);
            txtedad=(TextView)itemView.findViewById(R.id.idedaditems1);
            imgcheck=(ImageView)itemView.findViewById(R.id.imageCheck);

           // itemView.setOnClickListener(new View.OnClickListener() {
           //     @Override
           //     public void onClick(View v) {
//
           //     }
           // });
        }


    }
}

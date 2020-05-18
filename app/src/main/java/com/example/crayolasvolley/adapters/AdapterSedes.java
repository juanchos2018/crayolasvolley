package com.example.crayolasvolley.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.clases.ClsSedes;
import com.example.crayolasvolley.clases.Common;

import java.util.ArrayList;

public class AdapterSedes extends RecyclerView.Adapter<AdapterSedes.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<ClsSedes> listaSedes;
    Context context;
    public AdapterSedes(ArrayList<ClsSedes> listaSedes) {
        this.listaSedes = listaSedes;

    }
    private View.OnClickListener listener;
    public  void setOnClickListener(View.OnClickListener listener)
    {
        this.listener=listener;
    }


    int row_index=-1;
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sedes,parent,false);
        vista.setOnClickListener(this);
        return new  ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, final int position) {

        if (holder instanceof ViewHolderDatos){
            final ViewHolderDatos items =(ViewHolderDatos)holder;
            items.tvnombre_sede.setText(listaSedes.get(position).getNombre_sede());
            items.tvtelefono_sede.setText(listaSedes.get(position).getTelefono_sede());
            items.id_sede=listaSedes.get(position).getId_sede();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index=position;
                    Common.currenItem=listaSedes.get(position);
                    notifyDataSetChanged();
                }
            });

            if (row_index==position){
                holder.cardView.setBackgroundColor(Color.parseColor("#ff0000"));

            }else{
                holder.cardView.setBackgroundColor(Color.parseColor("#257df1"));
            }


        }

    }

    @Override
    public int getItemCount() {
        return listaSedes.size();
    }

    @Override
    public void onClick(View v) {

        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvnombre_sede,tvtelefono_sede;
        CardView cardView;
        int id_sede;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvnombre_sede=(TextView)itemView.findViewById(R.id.tv_nombresede);
            tvtelefono_sede=(TextView)itemView.findViewById(R.id.tv_telefonosede);
            cardView=(CardView)itemView.findViewById(R.id.cardvoiew);

        }
    }
}

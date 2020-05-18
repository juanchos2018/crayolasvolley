package com.example.crayolasvolley.clases;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.crayolasvolley.R;

import java.util.List;

public class ListaView extends ArrayAdapter<ClsSedes> {

    private Activity context;
    List<ClsSedes> sede;

    public ListaView(@NonNull Activity context, List<ClsSedes> sedes) {
        super(context, R.layout.item_lista_sedes, sedes);
        this.context = context;
        this.sede = sedes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.item_lista_sedes, null, true);

        Spinner textViewName = (Spinner) listViewItem.findViewById(R.id.tvsedes);

        ClsSedes sedes = sede.get(position);
       // textViewName.setText(sedes.getNombre_sede());

        textViewName.setId(sedes.getId_sede());

        return listViewItem;
    }
}

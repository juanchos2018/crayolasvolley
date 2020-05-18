package com.example.crayolasvolley.clases;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.AdapterProfesores;

import java.util.List;

public class SpinAdapter extends ArrayAdapter<ClsSedes> {

    private Context context;
    private  ClsSedes[] values;
    private List<ClsSedes> values2;



    public SpinAdapter(@NonNull Context context, int resource,ClsSedes[] values) {
        super(context, resource,values);
        this.context=context;
        this.values=values;
    }
    public int getCount(){
        return values.length;
    }
    public  ClsSedes getItem(int position){
        return values[position];
    }
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
     //  return super.getView(position, convertView, parent);
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values[position].getNombre_sede());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;

    }
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getNombre_sede());

        return label;
    }
}

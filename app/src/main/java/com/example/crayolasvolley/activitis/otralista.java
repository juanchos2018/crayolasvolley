package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crayolasvolley.R;
import com.example.crayolasvolley.adapters.SpinAdapter2;
import com.example.crayolasvolley.clases.ClsSedes;

import java.util.ArrayList;

public class otralista extends AppCompatActivity {

    private Spinner mySpinner;
    // Custom Spinner adapter (ArrayAdapter<User>)
    // You can define as a private to use it in the all class
    // This is the object that is going to do the "magic"
    private SpinAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otralista);

        ClsSedes[] users = new ClsSedes[2];

        ArrayList<ClsSedes> lista1=new ArrayList<ClsSedes>();

        ClsSedes o1 =new ClsSedes();
        o1.setId_sede(1);
        o1.setNombre_sede("sede 1");
        lista1.add(o1);

        ClsSedes o2=new ClsSedes();
        o2.setId_sede(2);
        o2.setNombre_sede("sede 2");

        lista1.add(o2);

        users[0] = new ClsSedes();
        users[0].setId_sede(1);
        users[0].setNombre_sede("sede 1");

        users[1] = new ClsSedes();
        users[1].setId_sede(2);
        users[1].setNombre_sede("sede 2");

        adapter = new SpinAdapter2(otralista.this,
                android.R.layout.simple_spinner_item,
                lista1);
        mySpinner = (Spinner) findViewById(R.id.miSpinner);
        mySpinner.setAdapter(adapter); // Set the custom adapter to the spinner
        // You can create an anonymous listener to handle the event when is selected an spinner item
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                ClsSedes user = adapter.getItem(position);
                // Here you can do the action you want to...
                Toast.makeText(otralista.this, "ID: " + user.getId_sede() + "\nName: " + user.getNombre_sede(),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
    }
}

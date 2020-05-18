package com.example.crayolasvolley.activitis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.example.crayolasvolley.R;

public class AgregarAlumno extends AppCompatActivity {


    ToggleButton toggleButton, toggleButton2;
    EditText etnnombrepoderado2, etcorreoapoderado2;
    EditText etnombreaalumno,etapellidoalumno,etedadalumno,etnombreapoderado1,etcorreoapoderado1,ettelefono;
    Button btnregistraralumno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumno);

        toggleButton=(ToggleButton)findViewById(R.id.idtoglebutton);
        toggleButton2=(ToggleButton)findViewById(R.id.idtoglebutton2);

        etnombreaalumno=(EditText)findViewById(R.id.idnombresalumno);
        etapellidoalumno=(EditText)findViewById(R.id.idapellidosalumno);
        etedadalumno=(EditText)findViewById(R.id.idedadalumno);
        etnombreapoderado1=(EditText)findViewById(R.id.idnombrepoderado1);
        etcorreoapoderado1=(EditText)findViewById(R.id.idcorreoapoderado1);
        etnnombrepoderado2=(EditText)findViewById(R.id.idnombreapoderado2);
        etcorreoapoderado2=(EditText)findViewById(R.id.idcorreoapoderado2);
        ettelefono=(EditText)findViewById(R.id.idteleofonoapoderado);
        btnregistraralumno=(Button)findViewById(R.id.idbotonregistraralumno);


        etnnombrepoderado2.setVisibility(View.GONE);
        etcorreoapoderado2.setVisibility(View.GONE);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togle1();
            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togle2();
            }
        });

        btnregistraralumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomnbrealumno=etnombreaalumno.getText().toString();
                String apellidoalumno=etapellidoalumno.getText().toString();
                String edadalumno=etedadalumno.getText().toString().trim();
                String nombreapodrado1=etnombreapoderado1.getText().toString();
                String correoapoderado1=etcorreoapoderado1.getText().toString().trim();
                String telefono=ettelefono.getText().toString().trim();

                String nombreapoderado2=etnnombrepoderado2.getText().toString();
                String correoapoderad2=etcorreoapoderado2.getText().toString().trim();
              //  String tokentareas=generartoken(telefono);

                registraralumno(nomnbrealumno,apellidoalumno,edadalumno,nombreapodrado1,correoapoderado1,nombreapoderado2,correoapoderad2,telefono);
            }
        });
    }

    private void registraralumno(String nomnbrealumno, String apellidoalumno, String edadalumno, String nombreapodrado1, String correoapoderado1, String nombreapoderado2, String correoapoderad2, String telefono) {


    }

    private void togle1(){
        if (toggleButton.isChecked()){

            etnnombrepoderado2.setVisibility(View.VISIBLE);
        }
        else{
            etnnombrepoderado2.setVisibility(View.GONE);

        }
    }
    private void togle2(){
        if (toggleButton2.isChecked()){
            etcorreoapoderado2.setVisibility(View.VISIBLE);
        }
        else{
            etcorreoapoderado2.setVisibility(View.GONE);
        }
    }
}

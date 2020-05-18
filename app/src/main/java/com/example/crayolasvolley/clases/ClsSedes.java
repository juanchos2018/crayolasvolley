package com.example.crayolasvolley.clases;

public class ClsSedes {

    int id_sede;
    String nombre_sede;
    String direccion_sede;
    String telefono_sede;


    public  ClsSedes(){

    }
    public ClsSedes(int id_sede, String nombre_sede, String direccion_sede, String telefono_sede) {
        this.id_sede = id_sede;
        this.nombre_sede = nombre_sede;
        this.direccion_sede = direccion_sede;
        this.telefono_sede = telefono_sede;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }

    public String getTelefono_sede() {
        return telefono_sede;
    }

    public void setTelefono_sede(String telefono_sede) {
        this.telefono_sede = telefono_sede;
    }

    @Override
    public String toString() {
        return this.getNombre_sede();            // What to display in the Spinner list.
    }
}

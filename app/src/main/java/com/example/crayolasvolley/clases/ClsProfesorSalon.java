package com.example.crayolasvolley.clases;

public class ClsProfesorSalon {

    int id_salon;
    String nombre_salon;
    String corre_salon;
    String nombre_sede;

    public ClsProfesorSalon(){

    }
    public ClsProfesorSalon(int id_salon, String nombre_salon, String corre_salon, String nombre_sede) {
        this.id_salon = id_salon;
        this.nombre_salon = nombre_salon;
        this.corre_salon = corre_salon;
        this.nombre_sede = nombre_sede;
    }

    public int getId_salon() {
        return id_salon;
    }

    public void setId_salon(int id_salon) {
        this.id_salon = id_salon;
    }

    public String getNombre_salon() {
        return nombre_salon;
    }

    public void setNombre_salon(String nombre_salon) {
        this.nombre_salon = nombre_salon;
    }

    public String getCorre_salon() {
        return corre_salon;
    }

    public void setCorre_salon(String corre_salon) {
        this.corre_salon = corre_salon;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }
}

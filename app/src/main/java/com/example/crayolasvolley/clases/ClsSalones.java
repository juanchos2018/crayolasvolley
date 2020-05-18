package com.example.crayolasvolley.clases;

public class ClsSalones {

    int id_salon;
    String nombre_salon;
    String correo_salon;
    String clave_salon;
    int id_sede;
    String sede_salon;

    public ClsSalones(){

    }
    public ClsSalones(int id_salon, String nombre_salon, String correo_salon, String clave_salon, int id_sede,String sede_salon) {
        this.id_salon = id_salon;
        this.nombre_salon = nombre_salon;
        this.correo_salon = correo_salon;
        this.clave_salon = clave_salon;
        this.id_sede = id_sede;
        this.sede_salon=sede_salon;
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

    public String getCorreo_salon() {
        return correo_salon;
    }

    public void setCorreo_salon(String correo_salon) {
        this.correo_salon = correo_salon;
    }

    public String getClave_salon() {
        return clave_salon;
    }

    public void setClave_salon(String clave_salon) {
        this.clave_salon = clave_salon;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public String getSede_salon() {
        return sede_salon;
    }

    public void setSede_salon(String sede_salon) {
        this.sede_salon = sede_salon;
    }
}

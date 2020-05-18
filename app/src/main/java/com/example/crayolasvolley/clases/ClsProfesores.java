package com.example.crayolasvolley.clases;

public class ClsProfesores {

    int id_profesor;
    String nombre_profesor;
    String apellido_profesor;
    String correo_profesor;
    String telefono_profesor;
    String direccion_profesor;
    String foto_profesor;

    public  ClsProfesores(){

    }

    public ClsProfesores(int id_profesor, String nombre_profesor, String apellido_profesor, String correo_profesor, String telefono_profesor, String direccion_profesor, String foto_profesor) {
        this.id_profesor = id_profesor;
        this.nombre_profesor = nombre_profesor;
        this.apellido_profesor = apellido_profesor;
        this.correo_profesor = correo_profesor;
        this.telefono_profesor = telefono_profesor;
        this.direccion_profesor = direccion_profesor;
        this.foto_profesor = foto_profesor;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getApellido_profesor() {
        return apellido_profesor;
    }

    public void setApellido_profesor(String apellido_profesor) {
        this.apellido_profesor = apellido_profesor;
    }

    public String getCorreo_profesor() {
        return correo_profesor;
    }

    public void setCorreo_profesor(String correo_profesor) {
        this.correo_profesor = correo_profesor;
    }

    public String getTelefono_profesor() {
        return telefono_profesor;
    }

    public void setTelefono_profesor(String telefono_profesor) {
        this.telefono_profesor = telefono_profesor;
    }

    public String getDireccion_profesor() {
        return direccion_profesor;
    }

    public void setDireccion_profesor(String direccion_profesor) {
        this.direccion_profesor = direccion_profesor;
    }

    public String getFoto_profesor() {
        return foto_profesor;
    }

    public void setFoto_profesor(String foto_profesor) {
        this.foto_profesor = foto_profesor;
    }
}

package com.example.crayolasvolley.clases;

public class ClsAlumnos {

    int id_alumno;
    String nombre_alumno;
     String apellido_alumno;
     String edad_alumno;
     String nombre_apoderado1;
     String nombre_apoderado2;
     String correo_apoderado1;
     String correo_apoderado2;
     String telefono_alumno;
     String rutafoto_alumno;
    private boolean isSelected = false;
     public ClsAlumnos(){

     }
    public ClsAlumnos(int id_alumno, String nombre_alumno, String apellido_alumno, String edad_alumno, String nombre_apoderado1, String nombre_apoderado2, String correo_apoderado1, String correo_apoderado2, String telefono_alumno, String rutafoto_alumno) {
        this.id_alumno = id_alumno;
        this.nombre_alumno = nombre_alumno;
        this.apellido_alumno = apellido_alumno;
        this.edad_alumno = edad_alumno;
        this.nombre_apoderado1 = nombre_apoderado1;
        this.nombre_apoderado2 = nombre_apoderado2;
        this.correo_apoderado1 = correo_apoderado1;
        this.correo_apoderado2 = correo_apoderado2;
        this.telefono_alumno = telefono_alumno;
        this.rutafoto_alumno = rutafoto_alumno;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellido_alumno() {
        return apellido_alumno;
    }

    public void setApellido_alumno(String apellido_alumno) {
        this.apellido_alumno = apellido_alumno;
    }

    public String getEdad_alumno() {
        return edad_alumno;
    }

    public void setEdad_alumno(String edad_alumno) {
        this.edad_alumno = edad_alumno;
    }

    public String getNombre_apoderado1() {
        return nombre_apoderado1;
    }

    public void setNombre_apoderado1(String nombre_apoderado1) {
        this.nombre_apoderado1 = nombre_apoderado1;
    }

    public String getNombre_apoderado2() {
        return nombre_apoderado2;
    }

    public void setNombre_apoderado2(String nombre_apoderado2) {
        this.nombre_apoderado2 = nombre_apoderado2;
    }

    public String getCorreo_apoderado1() {
        return correo_apoderado1;
    }

    public void setCorreo_apoderado1(String correo_apoderado1) {
        this.correo_apoderado1 = correo_apoderado1;
    }

    public String getCorreo_apoderado2() {
        return correo_apoderado2;
    }

    public void setCorreo_apoderado2(String correo_apoderado2) {
        this.correo_apoderado2 = correo_apoderado2;
    }

    public String getTelefono_alumno() {
        return telefono_alumno;
    }

    public void setTelefono_alumno(String telefono_alumno) {
        this.telefono_alumno = telefono_alumno;
    }

    public String getRutafoto_alumno() {
        return rutafoto_alumno;
    }

    public void setRutafoto_alumno(String rutafoto_alumno) {
        this.rutafoto_alumno = rutafoto_alumno;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }
}

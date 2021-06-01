package edu.upc.dsa.models;

public class Usuario {

    String correo;
    String apodo;
    String nombre;
    String apellido;
    String password;

    boolean insigniasbool[] = new boolean[6];   //declaring array

    public Usuario() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario(String correo, String apodo, String nombre, String apellido, String password) {
        this.correo = correo;
        this.apodo = apodo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public Usuario(String apodo, String password) {
        this.apodo = apodo;
        this.password = password;
    }

    public boolean[] getInsigniasbool() {
        return insigniasbool;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}

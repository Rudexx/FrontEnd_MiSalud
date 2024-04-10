package com.example.unbosque.Model;

public class UserRegistrationRequest {
    private String username;
    private String fecha_nacimiento;
    private String correo;
    private String password;
    private String telefono;
    private String profesion;

    public UserRegistrationRequest(String nombre, String fechaNacimiento, String correo,
                                   String contrasena, String telefono, String profesion) {
        this.username = nombre;
        this.fecha_nacimiento = fechaNacimiento;
        this.correo = correo;
        this.password = contrasena;
        this.telefono = telefono;
        this.profesion = profesion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
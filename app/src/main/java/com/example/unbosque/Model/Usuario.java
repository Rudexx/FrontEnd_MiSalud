package com.example.unbosque.Model;

public class Usuario{
    private String username;
    private String telefono;
    private String fecha_nacimiento; // Consider using a Date object for actual applications
    private String profesion;
    private String password;
    private String correo; // Nueva propiedad para el correo electrónico

    // Constructor actualizado con email
    public Usuario(String username, String telefono, String fecha_Nacimiento, String profesion, String password, String correo) {
        this.username = username;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.profesion = profesion;
        this.password = password;
        this.correo = correo; // Asignación de la nueva propiedad
    }

    // Getters y Setters actualizados para incluir email
    public String getUsername() {
        return username;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fecha_nacimiento;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getPassword() {
        return password;
    }

    public String getCorreo() { // Getter para email
        return correo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fecha_nacimiento = fechaNacimiento;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) { // Setter para email
        this.correo = email;
    }
}

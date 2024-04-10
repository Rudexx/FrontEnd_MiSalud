package com.example.unbosque;

public class Sintoma {
    private String nombre;
    private int valorDolor;

    public Sintoma(String nombre, int valorDolor) {
        this.nombre = nombre;
        this.valorDolor = valorDolor;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValorDolor() {
        return valorDolor;
    }

    public void setValorDolor(int valorDolor) {
        this.valorDolor = valorDolor;
    }
}
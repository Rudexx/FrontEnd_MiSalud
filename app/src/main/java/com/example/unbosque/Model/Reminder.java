package com.example.unbosque.Model;

public class Reminder {
    String fecha_inicio;
    String fecha_final;
    String frecuencia;
    String nombre_compuesto;

    public Reminder(String fecha_inicio, String fecha_final,
                    String frecuencia, String nombre_compuesto) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.frecuencia = frecuencia;
        this.nombre_compuesto = nombre_compuesto;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getNombre_compuesto() {
        return nombre_compuesto;
    }

    public void setNombre_compuesto(String nombre_compuesto) {
        this.nombre_compuesto = nombre_compuesto;
    }
}

package com.example.unbosque.Model;

public class ReminderRegistration {

    private String nombre_compuesto;
    private String fecha_inicio;
    private String fecha_final;
    private String correo;
    private String frecuencia;


    public ReminderRegistration(String nombre_compuesto, String fecha_inicio,
                                String fecha_final, String correo, String frecuencia) {
        this.nombre_compuesto = nombre_compuesto;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.correo = correo;
        this.frecuencia = frecuencia;
    }
}

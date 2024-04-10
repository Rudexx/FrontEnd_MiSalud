package com.example.unbosque;

public class DiagnosticoResultado {
    private String enfermedad;

    public DiagnosticoResultado() {
        // Inicialmente, no se conoce la enfermedad.
        this.enfermedad = "Desconocida";
    }

    // Getters y Setters
    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }
}
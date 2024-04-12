package com.example.unbosque.Model;

public class Disease {
    private String name;
    private String symptoms;
    private String treatment;

    // Constructor, getters y setters
    public Disease(String name, String symptoms, String treatment) {
        this.name = name;
        this.symptoms = symptoms;
        this.treatment = treatment;
    }

    public String getName() { return name; }
    public String getSymptoms() { return symptoms; }
    public String getTreatment() { return treatment; }
}

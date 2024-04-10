package com.example.unbosque;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
        private String nombre;
        private String correo;
        private List<Sintoma> sintomas;
        private List<String> condicionesPreexistentes;

        public Paciente(String nombre, String correo) {
            this.nombre = nombre;
            this.correo = correo;
            this.sintomas = new ArrayList<>();
            this.condicionesPreexistentes = new ArrayList<>();
        }

        public void agregarSintoma(Sintoma sintoma) {
            sintomas.add(sintoma);
        }

        public void agregarCondicionPreexistente(String condicion) {
            condicionesPreexistentes.add(condicion);
        }

        // Getters y Setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public List<Sintoma> getSintomas() {
            return sintomas;
        }

        public List<String> getCondicionesPreexistentes() {
            return condicionesPreexistentes;
        }
    }
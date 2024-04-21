package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.helloworld.R;
import com.example.unbosque.Model.Disease;

import java.util.ArrayList;
import java.util.List;

public class MedicinePage extends AppCompatActivity {
    private RecyclerView diseasesRecyclerView;
    private DiseaseAdapter adapter;
    private List<Disease> diseases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_medicine_page);

        // Inicializa la lista de enfermedades
        initializeDiseasesList();

        diseasesRecyclerView = findViewById(R.id.diseases_recycler_view);
        diseasesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiseaseAdapter(this, diseases);
        diseasesRecyclerView.setAdapter(adapter);
    }

    private void initializeDiseasesList() {
        diseases = new ArrayList<>();
        // Añade enfermedades a la lista
        diseases.add(new Disease("Cefalea", "Dolor de cabeza", ""));
        diseases.add(new Disease("Alergia Respiratoria", "Lagrimeo, Tos seca, Estornudos, Irritación en los ojos, Sinusitis, Asma", ""));
        diseases.add(new Disease("Fatiga Muscular por Esfuerzo", "Dolor muscular (leve, moderado, fuerte, al tocar), Inflamación muscular, Dolor al flexionar, Hematomas en la zona, Alivio con el reposo, Esfuerzo físico reciente", ""));
        diseases.add(new Disease("Alergia Cutánea o Tópica", "Enrojecimiento, Contacto con alguna sustancia no acostumbrada, Picazón, Inflamación (leve ≤ 4)", ""));
        diseases.add(new Disease("Pérdida de Voz por Esfuerzo", "Dolor de garganta, Pérdida de voz parcial o total, Uso continuo de la voz", ""));
        diseases.add(new Disease("Infección Gastrointestinal", "Deposiciones líquidas, Sangre en heces (disentería), Sensación de náuseas o vómitos", ""));
        diseases.add(new Disease("Gastritis", "Acidez en el estómago, Gases, Reflujo gástrico, Dolor de estómago", ""));
        diseases.add(new Disease("Amigdalitis", "Picazón de garganta, Dolor al tragar, Inflamación de amígdalas", ""));
        diseases.add(new Disease("Estreñimiento", "Dificultad al ir al baño, Inflamación o hinchazón en el abdomen, Dolor de estómago, Sensación de llenura", ""));
        diseases.add(new Disease("Resfriado", "Congestión nasal o nariz tapada", ""));
        diseases.add(new Disease("Dolor de garganta", "Tos, Estornudos, Malestar general leve, Dolor de cabeza, Fiebre leve", ""));

    }
}
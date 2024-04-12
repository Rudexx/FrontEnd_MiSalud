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
        diseases.add(new Disease("Gripe", "Fiebre, dolor de cabeza", "Descanso y medicación"));
        // Añadir más enfermedades aquí
        diseases.add(new Disease("Gripe", "Fiebre, dolor de cabeza", "Descanso y medicación"));
        diseases.add(new Disease("Gripe", "Fiebre, dolor de cabeza", "Descanso y medicación"));
        diseases.add(new Disease("Gripe", "Fiebre, dolor de cabeza", "Descanso y medicación"));

    }
}
package com.example.unbosque;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.helloworld.R;
public class DiseaseDetailActivity extends AppCompatActivity {

    private TextView textViewDiseaseName;
    private TextView textViewDiseaseSymptoms;
    private TextView textViewDiseaseTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_detail); // Asegúrate de que el nombre del layout sea correcto

        textViewDiseaseName = findViewById(R.id.textViewDiseaseName);
        textViewDiseaseSymptoms = findViewById(R.id.textViewDiseaseSymptoms);
        textViewDiseaseTreatment = findViewById(R.id.textViewDiseaseTreatment);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String symptoms = intent.getStringExtra("symptoms");
            String treatment = intent.getStringExtra("treatment");

            textViewDiseaseName.setText(name);
            textViewDiseaseSymptoms.setText(symptoms);
            textViewDiseaseTreatment.setText(treatment);
        }
    }
}
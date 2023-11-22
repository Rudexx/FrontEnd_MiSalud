package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class MedicinePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_medicine_page);
        List<String> medicineNames = new ArrayList<>();

        // Generate a list of medicines using a loop
        for (int i = 1; i <= 50; i++) {
            medicineNames.add("Medicina #" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, medicineNames);
        ListView listView = findViewById(R.id.medicine_list);
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_medicine_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicinePage.this, CreateMedicine.class);
                startActivity(intent);
            }
        });
    }

}
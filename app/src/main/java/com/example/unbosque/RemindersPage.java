package com.example.unbosque;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class RemindersPage extends AppCompatActivity {

    private CalendarView calendarView;
    private Button viewRemindersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_page);

        calendarView = findViewById(R.id.calendarView);
        viewRemindersButton = findViewById(R.id.viewRemindersButton);

        viewRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemindersPage.this, CreateReminder.class);
                startActivity(intent);
                Toast.makeText(RemindersPage.this, "Crear recordatorio", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.unbosque;

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
                // Handle button click action here
                // For example, you can open a new activity to display reminders
                // or perform any action you want.
                Toast.makeText(RemindersPage.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
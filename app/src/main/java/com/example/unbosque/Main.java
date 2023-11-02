package com.example.unbosque;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.helloworld.R;

import java.util.zip.Inflater;

public class Main extends AppCompatActivity {
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ImageButton chatbotButton = findViewById(R.id.chatbot_button);
        ImageButton remindersButton = findViewById(R.id.reminders_button);
        ImageButton medicinesButton = findViewById(R.id.medicines_button);
        ImageButton settingsButton = findViewById(R.id.settings_button);

        chatbotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle chatbot icon click
            }
        });

        remindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle reminders icon click
            }
        });

        medicinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle medicines icon click
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle settings icon click
            }
        });


    }
}
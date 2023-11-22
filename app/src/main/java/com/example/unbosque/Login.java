package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
        TextView register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {
            Toast.makeText(this, "Login :)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, Main.class);
            startActivity(intent);
        }else if(v.getId() == R.id.register){
            Toast.makeText(this, "Register :)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, RegisterPage.class);
            startActivity(intent);
        }
    }

}
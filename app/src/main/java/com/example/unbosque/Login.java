package com.example.unbosque;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.unbosque.Model.LoginResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import com.example.unbosque.Model.SharedPreferencesManager;
import com.example.unbosque.Model.UserApiService; // Replace with your actual package name
import com.example.unbosque.Model.LoginRequest;
import com.example.unbosque.Model.LoginResponse;

public class Login extends AppCompatActivity implements View.OnClickListener {
    public EditText editTextEmail;
    public EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
        TextView register = findViewById(R.id.register);
        register.setOnClickListener(this);

        editTextEmail = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            if(email.equals("prueba") && password.equals("12345")){
                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);
            }
            Intent intent = new Intent(Login.this, Login.class);
            Toast.makeText(this, "Login :)", Toast.LENGTH_SHORT).show();
            attemptLogin();

        }else if(v.getId() == R.id.register){
            Toast.makeText(this, "Register :)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, RegisterPage.class);
            startActivity(intent);
        }
    }
    private void attemptLogin() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .addConverterFactory(ScalarsConverterFactory.create())// Use this IP for emulator to access localhost
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
                .build();

        UserApiService service = retrofit.create(UserApiService.class);
        Call<String> call = service.loginUser(new LoginRequest(email, password));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseText = response.body();
                    // Since the response is plain text, you just log it or display it directly
                    Toast.makeText(Login.this, "Response: " + responseText, Toast.LENGTH_LONG).show();
                    SharedPreferencesManager manager = new SharedPreferencesManager(Login.this);
                    manager.saveUserEmail(email);
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                } else {
                    // Handle API error
                    Toast.makeText(Login.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle call failure
                Toast.makeText(Login.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
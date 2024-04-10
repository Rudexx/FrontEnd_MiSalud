package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.unbosque.Model.LoginRequest;
import com.example.unbosque.Model.UserApiService;
import com.example.unbosque.Model.UserRegistrationRequest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterPage extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextFechaNacimiento;
    private EditText editTextCorreo;
    private EditText editTextContrasena;
    private EditText editTextTelefono;
    private EditText editTextProfesion;
    private Button buttonRegistrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register_page);

        final EditText dateBornEditText = findViewById(R.id.date_born);
        dateBornEditText.setOnClickListener(view -> {
            // Get the current date
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Date Picker Dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterPage.this,
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        // Format and set the date in EditText
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateBornEditText.setText(selectedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });

        editTextCorreo = findViewById(R.id.email);
        buttonRegistrarse = findViewById(R.id.register_button);
        editTextTelefono = findViewById(R.id.cellphone);
        editTextContrasena = findViewById(R.id.passwordReg);
        editTextNombre = findViewById(R.id.name);
        editTextFechaNacimiento = findViewById(R.id.date_born);
        editTextProfesion = findViewById(R.id.profesion);


        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = editTextCorreo.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    editTextCorreo.setError("Ingresa una direccion de email correcta");
                    return; // Stop the function execution further
                }
                // Proceed with your registration process
                Toast.makeText(RegisterPage.this, "Email es correcto", Toast.LENGTH_SHORT).show();

                attemptRegistration();
            }
        });
    }

    private void attemptRegistration() {
        String email = editTextCorreo.getText().toString();
        String password = editTextContrasena.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String username = editTextNombre.getText().toString();
        String fecha_nacimiento = editTextFechaNacimiento.getText().toString();
        String profesion = editTextProfesion.getText().toString();

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
        Call<String> call = service.registerUser(new UserRegistrationRequest(username,fecha_nacimiento,email,
                password, telefono,  profesion));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseText = response.message();
                    // Since the response is plain text, you just log it or display it directly
                    Toast.makeText(RegisterPage.this, "Response: " + responseText, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterPage.this, Login.class);
                    startActivity(intent);
                } else {
                    // Handle API error
                    Toast.makeText(RegisterPage.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle call failure
                Toast.makeText(RegisterPage.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
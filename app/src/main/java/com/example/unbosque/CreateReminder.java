package com.example.unbosque;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.unbosque.Model.LoginRequest;
import com.example.unbosque.Model.ReminderRegistration;
import com.example.unbosque.Model.SharedPreferencesManager;
import com.example.unbosque.Model.UserApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateReminder extends AppCompatActivity {

    private EditText fromDate;
    private EditText toDate;
    private Spinner medicineTypeSpinner;
    private Spinner spinnerCada;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reminder_layout);
        initializeUI();
    }

    private void initializeUI() {
        // Initialize the date fields

        fromDate = findViewById(R.id.from_date);
        toDate = findViewById(R.id.to_date);
        name = findViewById(R.id.medicine_name);
        fromDate.setOnClickListener(view -> showDatePickerDialog(fromDate));
        toDate.setOnClickListener(view -> showDatePickerDialog(toDate));

        // Initialize medicine type spinner
        initializeMedicineTypeSpinner();

        // Initialize frequency spinner
        initializeFrequencySpinner();

        // Initialize confirm button
        initializeConfirmButton();
    }

    private void initializeMedicineTypeSpinner() {
        medicineTypeSpinner = findViewById(R.id.medicine_name5);
        String[] options = {"Pastillas", "Cucharadas", "Inyecciones"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineTypeSpinner.setAdapter(adapter);
    }

    private void initializeFrequencySpinner() {
        spinnerCada = findViewById(R.id.spinner_cada);
        String[] horasOptions = new String[48];
        for (int i = 1; i <= horasOptions.length; i++) {
            horasOptions[i - 1] = i + " Hora" + (i == 1 ? "" : "s");
        }
        ArrayAdapter<String> horasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horasOptions);
        horasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCada.setAdapter(horasAdapter);
    }

    private void initializeConfirmButton() {
        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(view -> showConfirmationDialog());
    }

    private void attemptRegistration() {
        String nombre = name.getText().toString();
        String desde = fromDate.getText().toString();
        String hasta = toDate.getText().toString();
        String frecuencia = spinnerCada.getSelectedItem().toString();
        SharedPreferencesManager manager = new SharedPreferencesManager(this);
        String correo = manager.getUserEmail();

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
        Call<String> call = service.registerReminder(new ReminderRegistration(nombre, desde, hasta,  correo, frecuencia));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseText = response.body();
                    // Since the response is plain text, you just log it or display it directly
                    Toast.makeText(CreateReminder.this, "Response: " + responseText, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateReminder.this, RemindersPage.class);
                    startActivity(intent);
                } else {
                    // Handle API error
                    Toast.makeText(CreateReminder.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle call failure
                Toast.makeText(CreateReminder.this, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Deseas crear el recordatorio y configurar las alarmas?");
        builder.setPositiveButton("OK", (dialog, id) -> {
            attemptRegistration();
            scheduleNotificationOneMinuteLater();  // Notificación en 1 minuto
            scheduleNotificationBasedOnHours();    // Notificación según las horas seleccionadas
            startMainActivity();
        });
        builder.setNegativeButton("Cancelar", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("ScheduleExactAlarm")
    private void scheduleNotificationOneMinuteLater() {
        Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        long oneMinuteInMillis = 30000; // 60,000 milliseconds in a minute

        // Programar la alarma para que se active en un minuto
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeAtButtonClick + oneMinuteInMillis, pendingIntent);
    }

    @SuppressLint("ScheduleExactAlarm")
    private void scheduleNotificationBasedOnHours() {
        String hoursText = spinnerCada.getSelectedItem().toString();
        int hours = Integer.parseInt(hoursText.split(" ")[0]); // Extraer el número de horas del texto del spinner
        long hoursInMillis = hours * 3600000; // Convertir horas a milisegundos

        Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, hours, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();

        // Programar la alarma para que se active después del número de horas seleccionado
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeAtButtonClick + hoursInMillis, pendingIntent);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void showDatePickerDialog(final EditText dateField) {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    dateField.setText(selectedDate);
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
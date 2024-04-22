package com.example.unbosque;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import org.threeten.bp.format.DateTimeFormatter;
import com.example.helloworld.R;
import com.example.unbosque.Model.ApiServiceBuilder;
import com.example.unbosque.Model.Reminder;
import com.example.unbosque.Model.SharedPreferencesManager;
import com.example.unbosque.Model.UserApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemindersPage extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private Button viewRemindersButton;
    private HashMap<String, List<String>> reminders;
    private int[] drawableIds;

    private ArrayList<Reminder> reminderList = new ArrayList<Reminder>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        reminders = new HashMap<>();

        AndroidThreeTen.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_page);
        viewRemindersButton = findViewById(R.id.viewRemindersButton);
        calendarView = findViewById(R.id.calendarView);

        drawableIds = new int[]{
                R.mipmap.calendar_icon, // Replace with your actual resource IDs
                R.mipmap.calendar_icon2,
                R.mipmap.calendar_icon3,
        };



        SharedPreferencesManager manager = new SharedPreferencesManager(this);
        String userEmail = manager.getUserEmail();


        if (userEmail != null) {
            getReminders(userEmail);  // Usa el correo para cargar los recordatorios específicos del usuario
        } else {
            Toast.makeText(RemindersPage.this, "Error de Sesion.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RemindersPage.this, Login.class);
            startActivity(intent);
        }


        viewRemindersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RemindersPage.this, CreateReminder.class);
                startActivity(intent);
            }
        });
    }

    private void verificarLista(){
        initializeReminders();
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                String selectedDate = String.format("%04d-%02d-%02d", date.getYear(), date.getMonth(), date.getDay());
                List<String> dayReminders = reminders.get(selectedDate);
                if (dayReminders != null && !dayReminders.isEmpty()) {
                    showDialog(dayReminders);
                } else {
                    Toast.makeText(RemindersPage.this, "No hay recordatorios para esta fecha.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferencesManager manager = new SharedPreferencesManager(this);
        String userEmail = manager.getUserEmail();

        if (userEmail != null) {
            getReminders(userEmail);  // Usa el correo para cargar los recordatorios específicos del usuario
            for (Map.Entry<String, List<String>> entry : reminders.entrySet()) {
                String date = entry.getKey(); // The date as the key of the HashMap
                List<String> remindersForDate = entry.getValue(); // The list of reminders for this date

                Toast.makeText(RemindersPage.this, (("Reminders on " + date + ":")), Toast.LENGTH_SHORT).show();  // Print the date
                for (String reminder : remindersForDate) {
                    Toast.makeText(RemindersPage.this, ("  - " + reminder), Toast.LENGTH_SHORT).show(); // Print each reminder
                }
            }
        } else {
            Toast.makeText(RemindersPage.this, "Error de Sesion.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RemindersPage.this, Login.class);
            startActivity(intent);
        }

    }

    private void getReminders(String email){
        reminderList.clear(); // Limpiar la lista existente antes de añadir nuevos elementos
        reminders.clear();

        UserApiService apiService = ApiServiceBuilder.createService();
        Call<List<Reminder>> call = apiService.getReminders(email);


        call.enqueue(new Callback<List<Reminder>>() {
            @Override
            public void onResponse(Call<List<Reminder>> call, Response<List<Reminder>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    for (int i = 0; i < response.body().size(); i++) {
                        reminderList.add(response.body().get(i));
                    }

                    for (Reminder reminder : response.body()) {
                        addReminderToCalendar(reminder);
                    }
                    verificarLista();
                } else {
                    Log.e("API Error", "Response Code: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Reminder>> call, Throwable t) {
                Log.e("API Call", "Error fetching reminders", t);
            }

        });
    }


    private void addReminderToCalendar(Reminder reminder) {
        LocalDate date = LocalDate.parse(reminder.getFecha_inicio(), DateTimeFormatter.ISO_DATE_TIME);
        String key = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (!reminders.containsKey(key)) {
            reminders.put(key, new ArrayList<>());
        }


        reminders.get(key).add("Nombre del Compuesto: " + reminder.getNombre_compuesto() +
                " - Tomar Cada: " + reminder.getFrecuencia() + " Hasta: " + reminder.getFecha_final().substring(0,10));
        System.out.println("key:   " + key + " " + reminders.get(key));

    }

    private int getDrawableIdForDay(CalendarDay day) {
        int dayOfMonth = day.getDay();
        return drawableIds[dayOfMonth % drawableIds.length];
    }
    private void initializeReminders() {
        if (reminders == null || reminders.isEmpty()) {
            return;
        }

        for (String dateStr : reminders.keySet()) {
            if (dateStr == null || dateStr.isEmpty()) {
                continue; // Skip this iteration if the date string is null or empty
            }

            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                CalendarDay day = CalendarDay.from(date.getYear(), date.getMonthValue(), date.getDayOfMonth());


                int drawableResId = getDrawableIdForDay(day);
                calendarView.addDecorator(new EventDecorator(this, drawableResId, day));
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + dateStr);
            }
        }
    }



    private void showDialog(List<String> reminders) {
        Log.d("RemindersPage", "showDialog called with: " + reminders);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recordatorios para esta fecha");
        if (reminders != null && !reminders.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for (String reminder : reminders) {
                message.append("\u2022 ").append(reminder).append("\n");
            }
            builder.setMessage(message.toString());
        } else {
            builder.setMessage("No hay recordatorios para esta fecha.");
        }
        builder.setPositiveButton("OK", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
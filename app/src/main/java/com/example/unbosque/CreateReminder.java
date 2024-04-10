package com.example.unbosque;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import com.example.helloworld.R;

public class CreateReminder extends AppCompatActivity {

    private EditText fromDate;
    private EditText toDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_reminder_layout);

        // Inicializa el Spinner
        Spinner medicineTypeSpinner = findViewById(R.id.medicine_name5);

        // Opciones para el spinner
        String[] options = {"Pastillas", "Cucharadas", "Inyecciones"};

        // Crea un ArrayAdapter usando el arreglo de opciones y un layout predeterminado para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

        // Especifica el layout a usar cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplica el adaptador al spinner
        medicineTypeSpinner.setAdapter(adapter);

        // Inicializa el Spinner para "Cada"
        Spinner spinnerCada = findViewById(R.id.spinner_cada);

        // Opciones para el spinner "Cada"
        String[] horasOptions = new String[24];
        for (int i = 1; i <= horasOptions.length; i++) {
            horasOptions[i - 1] = i + " Hora" + (i == 1 ? "" : "s");
        }

        // Crea un ArrayAdapter para "Cada"
        ArrayAdapter<String> horasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horasOptions);
        horasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCada.setAdapter(horasAdapter);

        fromDate = findViewById(R.id.from_date);
        toDate = findViewById(R.id.to_date);

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(fromDate);
            }
        });

        // Botón Confirmar
        Button confirmButton = findViewById(R.id.confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear y mostrar el AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateReminder.this);
                builder.setTitle("Confirmación");
                builder.setMessage("Recordatorio creado con éxito.");

                // Define el botón de OK y su acción
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Usuario hizo clic en OK, iniciar MainActivity
                        Intent intent = new Intent(CreateReminder.this, Main.class);
                        // Asegurarse de que la actividad se inicie como una nueva tarea
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish(); // Llamar a finish para asegurar que esta actividad se cierre
                    }
                });

                // Crear y mostrar el diálogo
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(toDate);
            }
        });
    }

    private void showDatePickerDialog(final EditText dateField) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                CreateReminder.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // +1 because January is zero
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateField.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}
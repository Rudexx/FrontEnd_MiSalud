package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.unbosque.Model.ApiServiceBuilder;
import com.example.unbosque.Model.SharedPreferencesManager;
import com.example.unbosque.Model.UserApiService;
import com.example.unbosque.Model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting_page);

        SharedPreferencesManager manager = new SharedPreferencesManager(this);
        String userEmail = manager.getUserEmail();


        if (userEmail != null) {
            consultarUsuario(userEmail);  // Usa el correo para cargar los recordatorios específicos del usuario
        } else {
            Toast.makeText(SettingPage.this, "Error de Sesion.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SettingPage.this, Login.class);
            startActivity(intent);
        }

        // Handler for the user data button

        // Modified Handler for the help button
        findViewById(R.id.button_ayuda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingPage.this);
                builder.setTitle("Necesitas ayuda?");
                builder.setMessage("Prueba nuestra guía de usuario: [Haz clic aquí](https://docs.google.com/document/d/1QlkhWvlOz30uuQapTN2i2Bv2mVBP4oN470RxWI5QX8A/edit)");
                builder.setPositiveButton("Abrir Guía", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1QlkhWvlOz30uuQapTN2i2Bv2mVBP4oN470RxWI5QX8A/edit"));
                        startActivity(browserIntent);
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // Handler for the about us button
        findViewById(R.id.button_quienes_somos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingPage.this);
                builder.setTitle("Quiénes Somos");
                builder.setMessage("Somos David Nogales y Joubert Alvarez Estudiantes en la Universidad del Bosque " +
                        "y Próximos ingenieros de sistemas, este proyecto surgió de la necesidad " +
                        "de ayudar a las personas que no tienen acceso a las consultas convencionales por " +
                        "falta de recursos económicos, tiempo, disponibilidad o cualquier otra causa!!");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void RealizarConsulta(String email){
        UserApiService apiService = ApiServiceBuilder.createService();
        Call<Usuario> call = apiService.getUser(email);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Usuario usuario = response.body(); // Assuming response.body() returns a Usuario object

                    // Create and show the AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(SettingPage.this);
                    builder.setTitle("Datos del Usuario");
                    builder.setMessage(
                            "Username: " + usuario.getUsername() + "\n" +
                                    "Teléfono: " + usuario.getTelefono() + "\n" +
                                    "Fecha de Nacimiento: " + usuario.getFechaNacimiento().substring(0,10) + "\n" +
                                    "Profesión: " + usuario.getProfesion() + "\n" +
                                    "Correo: " + usuario.getCorreo());

                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Log.e("API Error", "Response Code: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("API Call", "Error fetching user data", t);
            }
        });
    }
    private void consultarUsuario(String email){
        findViewById(R.id.button_datos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealizarConsulta(email);

            }
        });
    }

}


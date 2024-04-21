package com.example.unbosque.Model;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedPreferencesManager {
    private static final String PREF_NAME = "AppPreferences";
    private static final String KEY_USER_EMAIL = "userEmail";
    private SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();  // Guarda el correo electrónico de forma asincrónica
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, null);  // Devuelve null si no se ha guardado ningún correo
    }
}
package com.example.unbosque.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Esta es la acción que se realizará cuando la alarma se active
        Toast.makeText(context, "¡Alarma activada!", Toast.LENGTH_LONG).show();
        // Puedes añadir aquí más lógica según lo que necesites hacer cuando se dispare la alarma
    }
}
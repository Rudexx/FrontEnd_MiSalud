package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.helloworld.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class ChatPage extends AppCompatActivity {

    private ArrayList<ChatMessage> messages;
    private ChatAdapter chatAdapter;
    private ListView chatListView;
    private int fase = 0;
    private Spinner Spinner;
    private Button sendSymptomButton;
    private ArrayAdapter<String> symptomAdapter;
    String sintoma = "";
    private Paciente paciente = new Paciente("Prueba","prueba@gmail.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat_page);

        //droolsManager = new DroolsManager();

        //droolsManager.processPaciente(paciente);

        // Inicializamos la lista de mensajes
        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, messages);
        chatListView = findViewById(R.id.chatListView);
        chatListView.setAdapter(chatAdapter);

        // Inicializamos el spinner de síntomas
        Spinner = findViewById(R.id.symptomSpinner);
        symptomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.symptoms));
        symptomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(symptomAdapter);

        // Inicializamos el botón de enviar síntoma
        sendSymptomButton = findViewById(R.id.sendSymptomButton);

        // Manejador de clics del botón de enviar síntoma
        sendSymptomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el síntoma seleccionado
                String selectedSymptom = Spinner.getSelectedItem().toString();
                String mensaje = procesar(selectedSymptom);
                // Enviar el síntoma seleccionado al chat
                sendMessage(mensaje, false);
            }
        });

        // Enviamos el mensaje de bienvenida al iniciar la actividad
        sendMessage("Buenos días, soy Mila y estoy encargada de atender tus síntomas y ayudarte en lo que me sea posible. ¿Presentas algún síntoma hoy?", false);
    }

    private String procesar(String opcion){
        String respuesta = "";
        if(fase == 0){
            sendMessage("Síntoma seleccionado: " + opcion, true);
            sintoma = opcion;
            ArrayAdapter<CharSequence> painScaleAdapter = ArrayAdapter.createFromResource(
                    ChatPage.this, R.array.pain_scale, android.R.layout.simple_spinner_item);
            painScaleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner.setAdapter(painScaleAdapter);
            fase = 1;
            respuesta = "Lamento que te sientas asi, dime en una escala del 1 al 10, ¿que tanto te duele?";
            return respuesta;
        } else if (fase == 1) {
            sendMessage("Intencidad seleccionada: " + opcion, true);
            int intencidad = Integer.parseInt(opcion);
            Log.i("Sintoma", sintoma);
            Log.i("Intencidad", String.valueOf(intencidad));
            paciente.agregarSintoma(new Sintoma(sintoma, intencidad));

            if(Integer.parseInt(opcion) <= 6){
                respuesta = "Entiendo, ¿presentas algun otro sintoma?";
                fase = 2;

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        this, R.array.yes_no_options, android.R.layout.simple_spinner_item);

                // Especificar el diseño para las opciones desplegables
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Establecer el adaptador en el spinner
                Spinner.setAdapter(adapter);
                return respuesta;
            }else{
                fase = -1;
                respuesta = "En estos casos no estoy autorizado a atenderte, por favor dirigete al centro medico mas cercano";
                return respuesta;
            }
        } else if (fase == 2) {
            sendMessage(opcion,true);
            if(opcion.equals("Si")){
                respuesta = "Dime ¿que otro sintoma presentas?";
                fase = 0;

                Spinner.setAdapter(symptomAdapter);

                return respuesta;
            }else{
                sendMessage("Muy bien vamos a ver que puede ser", false);
                DiagnosticoHelper diagnosticoHelper = new DiagnosticoHelper(this);

            // Diagnosticar al paciente
                String enfermedad = diagnosticoHelper.diagnosticar(paciente);

                // Comprobar el resultado
                if("Danger".equals(enfermedad)) {
                    // Si el resultado es "Gripe", la regla funcionó como se esperaba
                    respuesta = "La enfermedad que estás reportando es muy seria para ser atendida por mí, por favor dirígete a un centro médico.";
                } else {
                    // Si el resultado no es "Gripe", revisar la regla y los datos de prueba
                    respuesta = "He encontrado que posiblemente tengas: " + enfermedad;
                }
                // Enviar la enfermedad detectada al chat
                // Reiniciar la fase y los síntomas para un nuevo diagnóstico
                fase = 0;
                sintoma = "";

                return respuesta;
            }
        } else if (fase == -1) {
            respuesta = "En estos casos no estoy autorizado a atenderte, por favor dirigete al centro medico mas cercano";
            return respuesta;
        } else{
            return "Se ha producido un error";
        }

    }

    // Método para enviar mensajes al chat
    private void sendMessage(String message, boolean usuario) {
        ChatMessage systemMessage = new ChatMessage(message, usuario);
        messages.add(systemMessage);
        chatAdapter.notifyDataSetChanged();
    }
}
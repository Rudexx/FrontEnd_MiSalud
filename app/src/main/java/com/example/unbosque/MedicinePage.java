package com.example.unbosque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.helloworld.R;
import com.example.unbosque.Model.Disease;

import java.util.ArrayList;
import java.util.List;

public class MedicinePage extends AppCompatActivity {
    private RecyclerView diseasesRecyclerView;
    private DiseaseAdapter adapter;
    private List<Disease> diseases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_medicine_page);

        // Inicializa la lista de enfermedades
        initializeDiseasesList();

        diseasesRecyclerView = findViewById(R.id.diseases_recycler_view);
        diseasesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiseaseAdapter(this, diseases);
        diseasesRecyclerView.setAdapter(adapter);
    }

    private void initializeDiseasesList() {
        diseases = new ArrayList<>();
        // Añade enfermedades a la lista
        diseases.add(new Disease("Cefalea/Migraña", "Tambien llamado mas comunmente como \"Migraña\" es une enfermedad que se caracteriza principalmente por: Dolor de cabeza", "En la mayoria de los casos esta enfermedad no requiere de atencion medica inmediata y se desaparece con el tiempo, algunos medicamentos que pueden ayudar son algunos analgesicos de baja potencia. Se debe tener cuidado si los sintomas persisten o de si el dolor viene despues de un golpe fuerte en la zona craneal."));
        diseases.add(new Disease(
                "Alergia Respiratoria",
                "Una alergia respiratoria puede manifestarse con síntomas como lagrimeo, tos seca, estornudos, irritación en los ojos, sinusitis y, en casos más graves, asma.",
                "Es importante consultar a un médico si experimentas estos síntomas con frecuencia o si interfieren con tu vida diaria. El tratamiento puede incluir antihistamínicos, descongestionantes y medidas para evitar los alérgenos."
        ));
        diseases.add(new Disease(
                "Fatiga Muscular por Esfuerzo",
                "La fatiga muscular por esfuerzo se caracteriza por dolor muscular (que puede variar de leve a fuerte), inflamación muscular, sensibilidad al tacto, dificultad para moverse, y en algunos casos, la presencia de hematomas en la zona afectada. Este tipo de fatiga suele ser el resultado de un esfuerzo físico intenso reciente.",
                "El reposo es fundamental para permitir que los músculos se recuperen. También se pueden usar compresas frías o calientes y analgésicos de venta libre para aliviar el malestar. Si el dolor persiste o empeora, es importante buscar atención médica para descartar lesiones graves."
        ));
        diseases.add(new Disease(
                "Alergia Cutánea o Tópica",
                "Una alergia cutánea o tópica se manifiesta a través de síntomas como enrojecimiento, picazón, inflamación leve y, a menudo, está relacionada con el contacto con sustancias a las que la piel no está acostumbrada.",
                "Es importante identificar y evitar las sustancias desencadenantes para prevenir futuras reacciones alérgicas. Se pueden usar cremas o lociones para aliviar la picazón y la inflamación. En casos graves, se debe buscar atención médica para recibir tratamiento adecuado."
        ));
        diseases.add(new Disease(
                "Pérdida de Voz por Esfuerzo",
                "La pérdida de voz por esfuerzo se manifiesta con síntomas como dolor de garganta, pérdida parcial o total de la voz, y generalmente está asociada con el uso continuo o excesivo de la voz.",
                "Para tratar la pérdida de voz por esfuerzo, es importante descansar la voz tanto como sea posible. Evitar hablar en voz alta o susurrar. Tomar líquidos tibios para ayudar a calmar la garganta. Si la pérdida de voz persiste durante más de una semana o se acompaña de otros síntomas, se debe buscar atención médica."
        ));
        diseases.add(new Disease(
                "Infección Gastrointestinal",
                "Una infección gastrointestinal se manifiesta con síntomas como deposiciones líquidas, presencia de sangre en las heces (disentería), y sensación de náuseas o vómitos.",
                "Es fundamental mantenerse bien hidratado durante una infección gastrointestinal, consumir alimentos blandos y fáciles de digerir, y evitar alimentos grasos o picantes. Si los síntomas persisten más de unos pocos días, o si hay signos de deshidratación, como sed extrema o disminución de la micción, se debe buscar atención médica de inmediato."
        ));
        diseases.add(new Disease(
                "Gastritis",
                "La gastritis se caracteriza por síntomas como acidez estomacal, gases, reflujo gástrico y dolor abdominal.",
                "Para aliviar los síntomas de la gastritis, es recomendable evitar alimentos irritantes como el café, el alcohol y los alimentos picantes. Se puede recurrir a medicamentos antiácidos para reducir la acidez estomacal y a cambios en la dieta, como comer comidas más pequeñas y frecuentes. Si los síntomas persisten o empeoran, es importante consultar a un médico para recibir tratamiento adecuado."
        ));
        diseases.add(new Disease(
                "Amigdalitis",
                "La amigdalitis se manifiesta con síntomas como picazón en la garganta, dolor al tragar y una notable inflamación de las amígdalas.",
                "Para aliviar los síntomas de la amigdalitis, se recomienda descansar y mantenerse bien hidratado. Los remedios caseros como gárgaras con agua tibia y sal pueden ayudar a reducir la incomodidad en la garganta. Es importante evitar fumar y el consumo de alimentos irritantes. Si los síntomas persisten más de unos pocos días o son severos, se debe buscar atención médica para un tratamiento adecuado, que puede incluir antibióticos si la causa es bacteriana."
        ));
        diseases.add(new Disease(
                "Estreñimiento",
                "El estreñimiento se caracteriza por síntomas como dificultad al ir al baño, inflamación o hinchazón en el abdomen, dolor abdominal y sensación de llenura.",
                "Para aliviar el estreñimiento, se recomienda aumentar la ingesta de fibra mediante frutas, verduras y cereales integrales. Beber suficiente agua y hacer ejercicio regularmente también puede ayudar a mejorar la regularidad intestinal. Si los síntomas persisten o son recurrentes, es importante consultar a un médico para descartar causas subyacentes y recibir tratamiento adecuado."
        ));
        diseases.add(new Disease(
                "Resfriado Común",
                "El resfriado común se manifiesta principalmente con congestión nasal o nariz tapada.",
                "Para aliviar los síntomas del resfriado, se recomienda descansar adecuadamente, mantenerse bien hidratado y evitar cambios bruscos de temperatura. Se pueden usar descongestionantes nasales de venta libre para aliviar la congestión nasal. En caso de fiebre alta, dolor de cabeza intenso o síntomas que empeoran, es importante consultar a un médico."
        ));
        diseases.add(new Disease(
                "Dolor de garganta",
                "El dolor de garganta se manifiesta con síntomas como tos, estornudos, malestar general leve, dolor de cabeza y fiebre leve.",
                "Para aliviar los síntomas del dolor de garganta, se recomienda descansar adecuadamente, beber líquidos tibios como té o caldo, evitar fumar y mantenerse alejado de los irritantes ambientales. Los caramelos para la garganta y los analgésicos de venta libre pueden proporcionar alivio temporal. Si el dolor de garganta persiste por más de una semana o se acompaña de fiebre alta, dificultad para tragar o respirar, es importante consultar a un médico."
        ));
    }
}
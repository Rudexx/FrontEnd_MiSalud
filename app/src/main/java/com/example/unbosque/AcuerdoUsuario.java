package com.example.unbosque;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.R;

public class AcuerdoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acuerdo_layout);

        Button btnAccept = findViewById(R.id.btnAccept);
        CheckBox checkBoxAgree = findViewById(R.id.checkBoxAgree);

        checkBoxAgree.setOnCheckedChangeListener((buttonView, isChecked) -> btnAccept.setEnabled(isChecked));

        String userAgreementText = "Este acuerdo de usuario establece las condiciones bajo las cuales puedes utilizar este prototipo de aplicación. Al aceptar estos términos, reconoces y aceptas lo siguiente:\n" +
                "\n" +
                "Prototipo: Esta aplicación es un prototipo en fase de prueba. Los prediagnósticos y recomendaciones proporcionados por el chatbot no son 100% seguros ni definitivos.\n" +
                "\n" +
                "Consulta Médica: En caso de no presentar mejorías, sentirte peor o tener cualquier duda sobre tu salud, es imperativo que consultes a un médico o profesional de la salud. Esta aplicación no sustituye el consejo, diagnóstico o tratamiento médico profesional.\n" +
                "\n" +
                "Responsabilidad del Usuario: El uso de esta aplicación y la confianza en los prediagnósticos proporcionados queda bajo la completa responsabilidad del usuario. No nos hacemos responsables por cualquier daño o perjuicio que pueda resultar del uso de esta aplicación.\n" +
                "\n" +
                "Al marcar la casilla de aceptación, confirmas que has leído, comprendido y aceptado los términos mencionados anteriormente.";
        TextView tvUserAgreement = findViewById(R.id.tvUserAgreement);
        tvUserAgreement.setText(userAgreementText);

        btnAccept.setOnClickListener(v -> {
            Intent intent = new Intent(AcuerdoUsuario.this, Main.class);
            startActivity(intent);

        });
    }
}

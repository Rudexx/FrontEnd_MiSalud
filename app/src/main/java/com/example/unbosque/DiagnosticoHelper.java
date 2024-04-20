package com.example.unbosque;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class DiagnosticoHelper {
    private KieSession kieSession = null;
    private boolean isInitialized = false;

    public DiagnosticoHelper(Context context) {
        //init(context);
    }

    private void init(Context context) {
        try {
            // Cargar el contenido del archivo .drl como un String
            String drlString = loadDrlFile(context, "rules/ejemploenfermedad.drl");

            // Configurar Drools para usar el String
            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            kieFileSystem.write("src/main/resources/dynamicRule.drl", kieServices.getResources().newByteArrayResource(drlString.getBytes()));
            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();

            // Comprobar errores de compilación
            if (kieBuilder.getResults().hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
                throw new RuntimeException("Errores de compilación en las reglas: " + kieBuilder.getResults().toString());
            }

            KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
            kieSession = kieContainer.newKieSession();

            isInitialized = true;
            Log.i("DiagnosticoHelper", "Inicialización de Drools exitosa con reglas desde String.");
        } catch (Exception e) {
            Log.e("DiagnosticoHelper", "Error al inicializar Drools con reglas desde String: " + e.getMessage(), e);
            isInitialized = false;
        }
    }

    private String loadDrlFile(Context context, String path) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public String diagnosticar(Paciente paciente) {
        if (paciente == null || paciente.getSintomas() == null || paciente.getSintomas().isEmpty()) {
            return "No se han proporcionado síntomas para el diagnóstico." + paciente.getSintomas();
        }

        boolean tieneDolorDeCabeza = false;
        boolean tieneLagrimeo = false;
        boolean tieneTos = false;
        boolean tieneEstornudos = false;
        boolean tieneIrritacionEnLosOjos = false;
        boolean tieneSinusitis = false;
        boolean tieneAsma = false;
        boolean tieneDolorMuscular = false;
        boolean tieneDolorMuscularAlTocar = false;
        boolean tieneInflamacionMuscular = false;
        boolean tieneDolorAlFlexionar = false;
        boolean tieneHematomasEnLaZona = false;
        boolean tieneEsfuerzoFisicoReciente = false;
        boolean tienePicazonDeGarganta = false;
        boolean tieneEnrojecimiento = false;
        boolean tieneDolorDeEstomago = false;
        boolean tienePicazon = false;
        boolean tieneInflamacion = false; // Considerar cómo manejarás el nivel de dolor
        boolean tieneDolorDeGarganta = false;
        boolean tienePerdidaDeVoz = false;
        boolean tieneDeposicionesLiquidas = false;
        boolean tieneSangreEnHeces = false;
        boolean tieneSensacionDeNauseasOVomitos = false;
        boolean tieneAcidezEnElEstomago = false;
        boolean tieneGases = false;
        boolean tieneReflujoGastrico = false;
        boolean tieneDificultadAlIrAlBaño = false;
        boolean tieneInflamacionOHinchazonEnElAbdomen = false;
        boolean tieneSensacionDeLlenura = false;
        boolean tieneCongestionNasalONarizTapada = false;
        boolean tieneMalestarGeneralLeve = false;
        boolean tieneDolorAlTragar = false;
        boolean tieneEstrenimiento = false;
        boolean tieneFiebre = false;
        boolean tieneInflamacionDeAmigdalas = false;
// Síntomas de exclusión para la cefalea
        boolean tieneNauseas = false;
        boolean tieneVomitos = false;
        boolean tieneSensibilidadALuz = false;
        boolean tieneSensibilidadAlRuido = false;
        boolean tieneGolpeFuerteEnLaCabeza = false;
        boolean tieneDificultadRespiratoria = false;


// Condiciones preexistentes relevantes
        boolean tieneEmbarazo = false;
        boolean tieneDiabetes = false;
        boolean tieneHipertension = false;


        List<Sintoma> sintomas = paciente.getSintomas();
        for (Sintoma sintoma : sintomas) {
            switch (sintoma.getNombre()) {
                case "Dolor de cabeza":
                    tieneDolorDeCabeza = true;
                    break;
                case "Lagrimeo":
                    tieneLagrimeo = true;
                    break;
                case "Tos":
                    tieneTos = true;
                    break;
                case "Estornudos":
                    tieneEstornudos = true;
                    break;
                case "Irritación en los ojos":
                    tieneIrritacionEnLosOjos = true;
                    break;
                case "Sinusitis":
                    tieneSinusitis = true;
                    break;
                case "Asma":
                    tieneAsma = true;
                    break;
                case "Dolor muscular":
                    // Asumiendo que esta variable es una combinación de diferentes niveles de dolor muscular
                    tieneDolorMuscular = true; // O establece el nivel de dolor correspondiente
                    break;
                case "Inflamación muscular":
                    tieneInflamacionMuscular = true;
                    break;
                case "Dolor al flexionar":
                    tieneDolorAlFlexionar = true;
                    break;
                case "Hematomas en la zona":
                    tieneHematomasEnLaZona = true;
                    break;
                case "Dolor de Estomago":
                    tieneDolorDeEstomago = true;
                    break;
                case "Esfuerzo físico reciente":
                    tieneEsfuerzoFisicoReciente = true;
                    break;
                case "Enrojecimiento":
                    tieneEnrojecimiento = true;
                    break;
                case "Picazón":
                    tienePicazon = true;
                    break;
                case "Inflamación":
                    tieneInflamacion = true;
                    break;
                case "Dolor de garganta":
                    tieneDolorDeGarganta = true;
                    break;
                case "Pérdida de voz parcial o total":
                    tienePerdidaDeVoz = true;
                    break;
                case "Deposiciones líquidas":
                    tieneDeposicionesLiquidas = true;
                    break;
                case "Sangre en heces":
                    tieneSangreEnHeces = true;
                    break;
                case "Sensación de náuseas o vómitos":
                    tieneSensacionDeNauseasOVomitos = true;
                    break;
                case "Acidez en el estómago":
                    tieneAcidezEnElEstomago = true;
                    break;
                case "Gases":
                    tieneGases = true;
                    break;
                case "Reflujo gástrico":
                    tieneReflujoGastrico = true;
                    break;
                case "Dificultad al ir al baño":
                    tieneDificultadAlIrAlBaño = true;
                    break;
                case "Inflamación o hinchazón en el abdomen":
                    tieneInflamacionOHinchazonEnElAbdomen = true;
                    break;
                case "Sensación de llenura":
                    tieneSensacionDeLlenura = true;
                    break;
                case "Congestión nasal":
                    tieneCongestionNasalONarizTapada = true;
                    break;
                case "Malestar general leve":
                    tieneMalestarGeneralLeve = true;
                    break;
                case "Fiebre":
                    tieneFiebre = true;
                    break;
                case "Nauseas":
                    tieneNauseas = true;
                    break;
                case "Vomitos":
                    tieneVomitos = true;
                    break;
                case "Picazon de Garganta":
                    tienePicazonDeGarganta = true;
                    break;
                case "Dolor al tragar":
                    tieneDolorAlTragar = true;
                    break;
                case "Estreñimiento":
                    tieneEstrenimiento = true;
                    break;
                case "Amigdalas Inflamadas":
                    tieneInflamacionDeAmigdalas = true;
                    break;
                case "Sensibilidad a la luz":
                    tieneSensibilidadALuz = true;
                    break;
                case "Sensibilidad al ruido":
                    tieneSensibilidadAlRuido = true;
                    break;
                case "Golpe fuerte en la cabeza":
                    tieneGolpeFuerteEnLaCabeza = true;
                    break;
                case "Dificultad al Respirar":
                    tieneDificultadRespiratoria = true;
                    break;
                default:
                    // Manejar otros casos si es necesario
                    break;
            }
        }
        String enfermedad = "";
        if (tieneDolorDeCabeza &&
                !tieneNauseas && !tieneVomitos && !tieneSensibilidadALuz &&
                !tieneSensibilidadAlRuido && !tieneGolpeFuerteEnLaCabeza) {

            // Diagnóstico de migraña si se cumplen las condiciones
            enfermedad = "Migraña";

            // Verificar si hay enfermedades preexistentes relevantes para alerta
            if (tieneEmbarazo || tieneDiabetes || tieneHipertension) {
                // Alerta para consulta médica
                System.out.println("Alerta: Consulta médica recomendada debido a condiciones preexistentes.");
            }
        } else if (tieneDolorDeCabeza && (tieneNauseas || tieneVomitos || tieneSensibilidadALuz ||
                tieneSensibilidadAlRuido || tieneGolpeFuerteEnLaCabeza)) {
            // Si el paciente tiene dolor de cabeza y alguno de los otros síntomas mencionados
            return "Por favor asistir a un centro de consulta medica, no estoy autorizado a tratarte";
        }

        if(tieneDificultadRespiratoria){
            return "Por favor asistir a un centro de consulta medica, no estoy autorizado a tratarte";
        }

        if (tieneLagrimeo && tieneTos && tieneEstornudos && tieneIrritacionEnLosOjos && tieneSinusitis && tieneAsma && !tieneDificultadRespiratoria) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Alergia respiratoria";
            }else {
                enfermedad = "Alergia respiratoria";
            }
        }

        if (tieneDolorMuscular  &&
                !tieneDolorMuscularAlTocar && !tieneHematomasEnLaZona && tieneInflamacionMuscular && tieneDolorAlFlexionar && tieneEsfuerzoFisicoReciente) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Fatiga muscular";
            }else {
            enfermedad = "Fatiga muscular";
            }
        }

        if (tieneEnrojecimiento && (tienePicazon || tieneInflamacion)) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Alergia cutánea o tópica";
            }else {
                enfermedad = "Alergia cutánea o tópica";
            }
        }

        if (tieneDolorDeGarganta && tienePerdidaDeVoz) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Pérdida de Voz por Esfuerzo";
            }else {
            enfermedad = "Pérdida de Voz por Esfuerzo";
            }
        }

        if (tieneDeposicionesLiquidas && tieneSangreEnHeces && tieneSensacionDeNauseasOVomitos) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Infección Gastrointestinal";
            }else {
                enfermedad = "Infección Gastrointestinal";
            }
        }

        if (tieneAcidezEnElEstomago && tieneGases && tieneReflujoGastrico && tieneDolorDeEstomago) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Gastritis";
            }else {
                enfermedad = "Gastritis";
            }
        }

        if (tienePicazonDeGarganta && tieneDolorAlTragar && tieneInflamacionDeAmigdalas) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Amigdalitis";
            }else {
                enfermedad = "Amigdalitis";
            }
        }

        if (tieneEstrenimiento && tieneDificultadAlIrAlBaño && tieneInflamacionOHinchazonEnElAbdomen && tieneDolorDeEstomago && tieneSensacionDeLlenura) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Estreñimiento";
            }else {
                enfermedad = "Estreñimiento";
            }
        }

        if (tieneCongestionNasalONarizTapada && tieneDolorDeGarganta && tieneTos &&
                tieneEstornudos && tieneMalestarGeneralLeve && tieneDolorDeCabeza &&
                tieneFiebre) {
            if (enfermedad != null) {
                enfermedad = "Síntomas coinciden con diferentes enfermedades, por favor consultar a un médico, enfermedades coincidentes: " + enfermedad + "Resfriado";
            }else {
                enfermedad = "Resfriado";
            }
        }

        if(enfermedad == ""){
            enfermedad = "No he podido identificar la enfermedad que tienes, por favor ve a un centro medico";
        }

        return enfermedad;
    }
}
package com.example.unbosque;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ProcesadorDrools {
    private KieSession kieSession;

    public ProcesadorDrools() {
        // Inicializar KieServices y obtener KieContainer
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        // Crear una nueva KieSession
        kieSession = kieContainer.newKieSession("ksession-rules");
    }

    public String diagnosticar(Paciente paciente) {
        DiagnosticoResultado resultado = new DiagnosticoResultado();

        // Insertar el objeto resultado y los datos del paciente en la sesión
        kieSession.insert(resultado);
        for (Sintoma sintoma : paciente.getSintomas()) {
            kieSession.insert(sintoma);
        }
        for (String condicion : paciente.getCondicionesPreexistentes()) {
            // Aquí puedes necesitar adaptar cómo manejas las condiciones preexistentes,
            // dependiendo de cómo están estructuradas tus reglas Drools.
            kieSession.insert(condicion);
        }

        // Ejecutar todas las reglas definidas
        kieSession.fireAllRules();

        // Cerrar la sesión después del uso
        kieSession.dispose();

        return resultado.getEnfermedad();
    }
}
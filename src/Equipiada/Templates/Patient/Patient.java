package Equipiada.Templates.Patient;

import Equipiada.Templates.IPatient.IPatient;
import Equipiada.Templates.ITableProducer.ITableProducer;


public class Patient implements IPatient {
    private String nome;
    private int patientN = 0;

    private ITableProducer producer;

    private String attributes[];
    private String patientInstance[];
    public Patient(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = producer.requestAttributes();
        String instances[][] = producer.requestInstances();

        patientN = (int)(Math.random() * instances.length);
        patientInstance = instances[patientN];

        System.out.println("Patient selected: " + patientN);
        System.out.println("Patient's disease: " + patientInstance[attributes.length - 1]);
    }

    public String ask(String question) {
        String result = "unknown";

        for (int a = 0; a < attributes.length - 1; a++)
            if (question.equalsIgnoreCase(attributes[a]))
                result = (patientInstance[a].equals("t")) ? "yes" : "no";

        return result;
    }

    public boolean finalAnswer(String answer) {
        boolean result = false;
        if (answer.equalsIgnoreCase(patientInstance[attributes.length - 1]))
            result = true;
        return result;
    }
}
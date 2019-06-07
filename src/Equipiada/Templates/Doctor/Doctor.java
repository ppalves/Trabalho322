package Equipiada.Templates.Doctor;

import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IResponder.IResponder;
import Equipiada.Templates.ITableProducer.ITableProducer;

public class Doctor implements IDoctor {
    private String nome;
    private int patientN = 0;

    public Doctor(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    private ITableProducer producer;
    private IResponder responder;

    public void connect(ITableProducer producer) {
        this.producer = producer;
    }

    public void connect(IResponder responder) {
        this.responder = responder;
    }

    public void startInterview() {
        String attributes[] = producer.requestAttributes();
        String instances[][] = producer.requestInstances();
        String resultado[] = new String[attributes.length-1];
        boolean linhaTrue = false;


        for (int a = 0; a < attributes.length - 1; a++){
            System.out.println("Question: " + responder.ask(attributes[a]));
            if("no".equals(responder.ask(attributes[a])))
                resultado[a] = "f";
            else
                resultado[a] = "t";
        }


        for(int a = 0; a < instances.length; a++){
            linhaTrue = true;
            for(int b = 0; b < attributes.length-1; b++){
                if(!resultado[b].equals(instances[a][b]))
                    linhaTrue = false;
            }
            if(linhaTrue){
                /* Se der match, dá o diagnostico e para - preferência ao primeiro diagnóstico */
                patientN = a;
                break;
            }
        }


        System.out.println("Disease guess: " + instances[patientN][attributes.length - 1]);
        boolean result = responder.finalAnswer(instances[patientN][attributes.length - 1]);
        System.out.println("Result: " + ((result) ? "I am right =)" : "I am wrong =("));
    }

}

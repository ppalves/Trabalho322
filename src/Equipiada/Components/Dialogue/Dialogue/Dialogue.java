package Equipiada.Components.Dialogue.Dialogue;

import Equipiada.Components.Dialogue.IDialogue.IDialogue;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;

import java.util.Random;

public class Dialogue implements IDialogue {
    private IPatient patient;
    private IDoctor doctor;
    private boolean badditional;
    private boolean bvirose;
    private boolean bidentifiers;
    private int state;
    private Random rand;

    public Dialogue(){
        badditional = false;
        bvirose  = false;
        bidentifiers  = false;
        state = 0;
        rand = new Random();
        patient = null;
        doctor = null;
    }
    public void connect(IPatient p, IDoctor d){
        patient = p;
        doctor = d;
    }
    public void additional(boolean additional){
        badditional = additional;
    }
    public void virose(boolean virose){
        bvirose = virose;
    }
    public void identifiers(boolean identifiers){
        bidentifiers = identifiers;
    }
    private String compose(String s,int speaker){
        String result = null;
        if(bidentifiers) {
            result = "[";
            if(speaker == 0)
                result += patient.getNome();
            else
                result += doctor.getNome();
            result += "]  \t: ";
        }
        result += s;
        return result;
    }

    public void start(){
        if(patient == null || doctor == null)
            throw new IllegalArgumentException("Needs Patient and Doctor to start Dialogue");

        state = 0;
        String[] DataStarter1 = {"Bom dia, tudo bem?", "Bom dia, tudo certo?", "Boa manhã.", "Boa noite.","Eai meu encapsulado?"};
        String[] DataStarter2 = {"Bom dia, tudo certo.", "Bom dia, tudo ótimo.", "Fala, meu instanciado.", "Boa noite."};
        String[] DataDoctor = {"Vamos começar, é sua primeira vez aqui?", "Me fale dos seus problemas","asdasdas","asdad3er","asdasdasd"};
        String[] DataPatient = {"Eu estou sentindo uma dorzinha", "Ando meio esquisto", "Sei lá, minha mãe me mandou aqui","asfe","asdasd"};

        if(badditional){
            int trand = rand.nextInt(100);
            int starter = trand%2;
            int follower = (starter + 1)%2;
            int size = trand/20+1;
            System.out.println(compose(DataStarter1[trand%(DataStarter1.length)], starter));
            System.out.println(compose(DataStarter2[trand%(DataStarter2.length)], follower));
            for(int a = 0; a < size; a++){
                int select = 0;
                if(a%2==0){
                    System.out.println(compose(DataDoctor[(trand+a)%DataDoctor.length], 1));
                }
                else {
                    System.out.println(compose(DataPatient[(trand+a)%DataPatient.length], 0));
                }
            }
        }
    }

    public String questions(String question){
        String result = null;
        return result;
    }
    public String answers(String answer){
        String result = null;
        return result;
    }
}

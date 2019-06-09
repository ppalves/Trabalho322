package Equipiada.Components.Dialogue.Dialogue;

import Equipiada.Components.Dialogue.IDialogue.IDialogue;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Componente de diálogo para o trabalho de MC322 - Equipiada
 * @author Andreis Purim
 * @version V1.0.1
 * Adaptado para o cógigo do Leonardo
 */

/* **************************************** DIALOGUE ******************************* */
public class Dialogue implements IDialogue {
    private IPatient patient;       //
    private IDoctor doctor;         // Paciente e Doutor
    private boolean badditional;    //
    private boolean bvirose;        // Booleans para tipos
    private boolean bidentifiers;   //
    private Random rand;            // Randomizador

    /* *************************** Strings da máquina de Estados **************************/
    private String[] theatricalDoc; //
    private String[] theatricalPat; // Teátricos
    private String[] DataStarter1;  //
    private String[] DataStarter2;  // Início
    private String[] DataDoctor;    //
    private String[] DataPatient;   //
    private String[] StartInt;      //
    private String[] DataQuestion;  // Entrevista
    private String[] DataAnswer;    //
    private String[] DataDiagnose;  //
    private String[] Ypossibles;    //
    private String[] Npossibles;    // Arrumadores
    private String[] prepared;      //
    private String[] preMade;       //

    public Dialogue(){
        badditional = true;     //
        bvirose  = true;        //
        bidentifiers  = true;   // Cria novas variáveis
        rand = new Random();    //
        patient = null;         //
        doctor = null;          //
        /* **************************** Leitura de Arquivo **************************** */
        try {
            FileReader arquivo = new FileReader("src/Equipiada/Components/Dialogue/DialogueText/DialogueText.txt");              //
            BufferedReader formatado = new BufferedReader(arquivo); // Leitores da
            String linha = formatado.readLine();                    // Database
            String[] buffer;                                        //
            /* ************************** Maquina de Estados ************************** */
            while (linha != null) {
                if(linha.charAt(0)=='0'||linha.charAt(0)=='1') {
                    buffer = (linha.substring(4)).split( "--");
                    switch(Integer.parseInt(linha.substring(0, 2))) {
                        case 1 : theatricalPat = buffer; break;
                        case 2 : theatricalDoc = buffer; break;
                        case 3 : DataStarter1 = buffer; break;
                        case 4 : DataStarter2 = buffer; break;
                        case 5 : DataDoctor = buffer; break;
                        case 6 : DataPatient = buffer; break;
                        case 7 : StartInt = buffer; break;
                        case 8 : DataQuestion = buffer; break;
                        case 9 : DataAnswer = buffer; break;
                        case 10: DataDiagnose = buffer; break;
                        case 11: Ypossibles = buffer; break;
                        case 12: Npossibles = buffer; break;
                        case 13: prepared = buffer; break;
                        case 14: preMade = buffer; break;
                    }
                }
                linha = formatado.readLine();
            }
            arquivo.close();
        }catch (IOException erro) {
            System.out.println("Erro, DialogueText.txt não encontrado");
        }
    }
    /* ************************** Conect ************************** */
    public void connect(IPatient p, IDoctor d){
        patient = p;
        doctor = d;
    }
    /* ************************** Adicionais ************************** */
    public void additional(boolean additional){
        badditional = additional;
    }
    public void virose(boolean virose){
        bvirose = virose;
    }
    public void identifiers(boolean identifiers){
        bidentifiers = identifiers;
    }
    /* ************************** Compõe Frases ************************** */
    /* A base da composição de frases */
    private String compose(String s,int speaker){
        StringBuilder result = new StringBuilder("");
        if(bidentifiers){                           //
            result.append("[");                     //
            if(speaker == 0)                        // Aqui só vai fazer
                result.append(patient.getNome());   // identificadores
            else                                    //
                result.append("Dr. ").append(doctor.getNome());                         // Adiciona Dr.
            int spacers = patient.getNome().length() - 4 - doctor.getNome().length();   // Acha espaços
            result.append("]");         //
            if(spacers < 0)             // Ajusta espaçamento
                if(speaker == 0)        //
                    for(int a = 0; a < -spacers; a++)
                        result.append(" ");
            result.append(": ");
        }
        result.append(s);                           //
        if(bidentifiers) {                          // Aqui vai adicionar teátricos
            int action = rand.nextInt( 50); //
            if(speaker == 0)                        //
                if (action < theatricalPat.length)
                    result.append(" ").append(theatricalPat[action]);   //
            if(speaker == 1)                                            // E adiciona na frase
                if (action < theatricalDoc.length)                      //
                    result.append(" ").append(theatricalDoc[action]);   //
        }
        return result.toString();
    }

    /* ************************** Start ************************** */
    public void start(){
        // Impede que você inicie sem paciente e doutor
        if(patient == null || doctor == null)
            throw new IllegalArgumentException("Needs Patient and Doctor to start Dialogue");
        int trand = rand.nextInt(100);  //
        int starter = trand%2;                  // Randômicos
        if(badditional){                        // Diálogos adicionais do início
            int follower = (starter + 1)%2;     // As operações são modulares
            int size = trand/20+1;              //
            String s1 = DataStarter1[trand%(DataStarter1.length)];  //
            String s2 = DataStarter2[trand%(DataStarter2.length)];  // Randomicamente inicia
            System.out.println(compose(s1, starter));               // a conversa
            System.out.println(compose(s2, follower));              //
            for(int a = 0; a < size; a++)
                if(a%2==0) {
                    s1 = DataDoctor[(trand + a) % DataDoctor.length];   //
                    System.out.println(compose(s1, 1));         //
                }
                else {
                    s2 = DataPatient[(trand + a) % DataPatient.length]; // Paciente e doutor conversando
                    System.out.println(compose(s2, 0));         //
                }
            s1 = StartInt[trand%(StartInt.length)];         // Inicia a entrevista
            System.out.println(compose(s1,1));      //
        }
    }

    /* ************************** Compõe Sintomas ************************** */
    private String composeSymptoms(String s1,String s2){
        StringBuilder result = new StringBuilder(s1);      //
        String[] sArray = s2.split("_");            // Simplesmente separa
        for (String s : sArray) {                         // sintomas
            result.append(" ").append(s);                 //
        }
        result.append("?");
        return compose(result.toString(),1);
    }

    /* ************************** Pergunta ************************** */
    public String questions(String question){
        int trand = rand.nextInt(100);
        return composeSymptoms(DataQuestion[trand%(DataQuestion.length)],question);
    }

    /* ************************** Compõe Resposta ************************** */
    private String composeAnswer(String s1,String s2){
        StringBuilder result = new StringBuilder(s1);
        if(s2.equalsIgnoreCase("t"))        //
            result.append(" sim.");                      // verifica se não é
        else if(s2.equalsIgnoreCase("f"))   // t ou f
            result.append(" não.");                      //
        else{
            for (String ypossible : Ypossibles)     //
                if (s2.equalsIgnoreCase(ypossible)) //
                    result.append(" sim.");         // Se não, verifica
            for (String npossible : Npossibles)     // as possibilidades
                if (s2.equalsIgnoreCase(npossible)) //
                    result.append(" não.");         //
        }
        return compose(result.toString(),0);
    }

    /* ************************** Responde ************************** */
    public String answer(String answer){
        int trand = rand.nextInt(100);
        return composeAnswer(DataAnswer[trand%(DataAnswer.length)],answer);
    }

    /* ************************** Diagnostico ************************** */
    public String diagnose(String disease){
        int trand = rand.nextInt(100);
        boolean found = false;
        StringBuilder result = new StringBuilder(DataDiagnose[trand % (DataDiagnose.length)]);
        // Começa gerando uma string da database
        if(bvirose)
            for (int a = 0; a < prepared.length; a++)           //
                if (disease.equalsIgnoreCase(prepared[a])) {    // Se a doença já foi registrada
                    result.append(preMade[a]);                  // na database, substitui
                    found = true;                               //
                }
        if (!found) {
            String[] sArrays = disease.split("_");  // Separa espaços
            if(bvirose) {                                  // Se não foi encontrada
                if (sArrays.length > 1)                    // ele dá um jeito de virosear
                    sArrays[1] = "virose";                 //
            }
            for (String sArray : sArrays) {
                result.append(" ").append(sArray);
            }
        }
        result.append(".");
        return compose(result.toString(),1);
    }
}

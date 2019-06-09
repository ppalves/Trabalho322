package Equipiada.Components.Dialogue.Example;

import Equipiada.Components.Dialogue.Dialogue.Dialogue;
import Equipiada.Components.Dialogue.DialogueFactory.DialogueFactory;
import Equipiada.Templates.DataSetComponent.DataSetComponent;
import Equipiada.Templates.Doctor.Doctor;
import Equipiada.Templates.IDataSet.IDataSet;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;
import Equipiada.Templates.Patient.Patient;
//import Equipiada.Components.Dialogue.DialogueText;

public class Dialogue_Example {
    public static void main(String[] args) {
        // Olá, seja bem vindo ao componente de Diálogos, vamos aprender a utlizar o componente.
        // Começando com os templates utilizados para Doutor e Paciente
        String tablePath = "src/Equipiada/Templates/Tables/zombie-health-spreadsheet-ml-training.csv";
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource(tablePath);

        IPatient aPatient = new Patient("Marcos");
        aPatient.connect(dataset);

        IDoctor cDoctor = new Doctor("Andreis");
        cDoctor.connect(dataset);

        cDoctor.connect(aPatient);

        // Agora para o diálogo
        Dialogue d = DialogueFactory.createDialogue();

        // Vamos conectar o atual paciente e doutor
        d.connect(aPatient,cDoctor);

        // Vamos começar setando algumas coisas, se os nossos zumbis possuem getNome(), podemos usar identifiers
        d.identifiers(true);
        // Vamos ligar dialogos adicionais
        d.additional(true);
        // E vamos transformar as doenças em viroses!
        d.virose(true);
        // Vamos começar a máquina de estados;
        d.start();

        // Para fins de exemplo, estou fazendo as questões e respostas na main!
        // Vamos mandar a pergunta ao paciente;
        System.out.println(d.questions("paralysis"));
        // Vamos responder que sim, veja que ele entende se eu responder sim, t e yes
        System.out.println(d.answer("yes"));
        // Vamos diagnosticar com bacterial "virose"
        System.out.println(d.diagnose("bacterial_infection"));
        // Vamos tentar uma doença nova composta
        System.out.println(d.diagnose("marconites_infecciosa"));
        //Ou uma doença simples
        System.out.println(d.diagnose("marconites"));
    }
}

package Equipiada.Main;

import java.util.ArrayList;
import java.util.List;

import Equipiada.Templates.DataSetComponent.DataSetComponent;
import Equipiada.Templates.ITreeDoctor.ITreeDoctor;
import Equipiada.Components.TreeDoctor.TreeDoctor;
import Equipiada.Templates.IDataSet.IDataSet;
import Equipiada.Templates.IPatient.IPatient;
import Equipiada.Templates.Patient.Patient;
import pt.clubedohardware.dataorganizer.IDataOrganizer;
import pt.clubedohardware.dataorganizer.DataOrganizer;
import pt.clubedohardware.node.Node;
import pt.clubedohardware.node.Tree;
import Equipiada.Components.Dialogue.Dialogue.Dialogue;



public class Main{
    public static void main(String[] args){

        String path = "/home/pupo/Documents/graduação/mc322/Trabalho322/src/Equipiada/Templates/Tables/zombie-health-spreadsheet-ml-training.csv";
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource(path);

        IPatient aPatient = new Patient("Elton");
        aPatient.connect(dataset);

        ITreeDoctor treeDoctor = new TreeDoctor("Drauzio Zombierela");
        treeDoctor.connect(dataset);

        treeDoctor.connect(aPatient);

        System.out.println("\n==============================\n");

        Dialogue dialogue = new Dialogue();

        dialogue.connect(aPatient, treeDoctor);
        dialogue.additional(true);
        dialogue.virose(false);
        dialogue.identifiers(true);
        dialogue.start();

        IDataOrganizer dataOrganizer = new DataOrganizer();

        String[][] instances = dataset.requestInstances();
        String[] attributes = dataset.requestAttributes();

        List<String> diseases = dataOrganizer.diseaseFilter(instances);
        int [][] symptomFrequency = dataOrganizer.symptomFilter(instances, diseases);
        Tree tree = dataOrganizer.treeMaker(diseases, symptomFrequency, instances);


        List<Integer> keySymptoms = tree.getKeySymptoms();
        List<Integer> diseasesNumbers = new ArrayList<>();

        for (Integer symptom: keySymptoms) {
            System.out.println(dialogue.questions(attributes[symptom]));

            if(treeDoctor.askPatiente(attributes[symptom]).equalsIgnoreCase("yes"))
                diseasesNumbers.add(tree.getKSDiagnostic(symptom));

            System.out.println(dialogue.answer(treeDoctor.askPatiente(attributes[symptom])));
        }


        if (diseasesNumbers.isEmpty()) {

            Node raiz = tree.getRoot();

            while (!raiz.getDiagnostico()) {
                System.out.println(dialogue.questions(attributes[raiz.getSymptom()]));
                if (treeDoctor.askPatiente(attributes[raiz.getSymptom()]).equalsIgnoreCase("yes"))
                    raiz = raiz.getEsquerdo();
                else
                    raiz = raiz.getDireito();

                System.out.println(dialogue.answer(treeDoctor.askPatiente(attributes[raiz.getSymptom()])));
            }

            diseasesNumbers = raiz.getDiseases();

        }

        for (Integer diseaseNumber: diseasesNumbers) {
            System.out.println(dialogue.diagnose(diseases.get(diseaseNumber)));
        }
    }
}
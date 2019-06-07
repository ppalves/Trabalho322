package Equipiada.Components.Dialogue.Example;


import Equipiada.Components.Dialogue.Dialogue.Dialogue;
import Equipiada.Templates.DataSetComponent.DataSetComponent;
import Equipiada.Templates.Doctor.Doctor;
import Equipiada.Templates.IDataSet.IDataSet;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;
import Equipiada.Templates.Patient.Patient;

public class Main{
    public static void main(String[] args) {
        String tablePath = "C:\\Users\\Micro\\IdeaProjects\\Statistic\\src\\zombie-health-spreadsheet-ml-training.csv";
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource(tablePath);

        IPatient aPatient = new Patient("Desu");
        aPatient.connect(dataset);

        IDoctor cDoctor = new Doctor("Andreis");
        cDoctor.connect(dataset);

        cDoctor.connect(aPatient);
        cDoctor.startInterview();

        Dialogue d = new Dialogue();
        d.connect(aPatient,cDoctor);
        d.additional(true);
        d.identifiers(true);
        d.start();

    }
}

package Equipiada.Components.Debugger.Example;

import Equipiada.Components.Debugger.Debugger.Debugger;
import Equipiada.Templates.DataSetComponent.DataSetComponent;
import Equipiada.Templates.Doctor.Doctor;
import Equipiada.Templates.IDataSet.IDataSet;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;
import Equipiada.Templates.Patient.Patient;

public class DebuggerExample {
    public static void main(String[] args) {
        String tablePath = "src/Equipiada/Templates/Tables/zombie-health-spreadsheet-ml-training.csv";
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource(tablePath);

        IPatient aPatient = new Patient("Guilherme");
        aPatient.connect(dataset);

        IDoctor cDoctor = new Doctor("Asdrubal");
        cDoctor.connect(dataset);

        Debugger debugger = new Debugger(dataset);
        debugger.connect(cDoctor, aPatient);
        debugger.debugPatient();
        debugger.debugDoctor();
    }
}

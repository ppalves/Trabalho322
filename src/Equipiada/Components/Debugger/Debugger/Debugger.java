package Equipiada.Components.Debugger.Debugger;

import Equipiada.Components.Debugger.IDebugger.IDebugger;
import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;
import Equipiada.Templates.ITableProducer.ITableProducer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Integer;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Debugger implements IDebugger {
    IDoctor doctor;
    IPatient patient;
    private String[] attributes;
    private String[][] instances;
    private int diseaseHolder;
    private String[] symptomsHolder;
    private int resultHolder;

    public Debugger(ITableProducer producer) {
        attributes = producer.requestAttributes(); // Pega atributos
        instances = producer.requestInstances();   //
    }

    public void connect(IDoctor doc, IPatient pat) {
        this.doctor = doc;
        this.patient = pat;
    }

    public void debugPatient() {
        try {
            PrintWriter file = new PrintWriter(new FileWriter("src/Equipiada/Components/Debugger/DebugLog/DebugLog.txt", true));
            file.println("================================ PATIENT ================================");
            Class c = this.patient.getClass();
            Method[] m = c.getDeclaredMethods();
            file.println("--------------- METODOS ---------------");
            for (int i = 0; i < m.length; i++) {
                String[] methodSplit = (m[i].toString()).split(" |\\$");
                String result = methodSplit[0] + " " + methodSplit[2];
                file.println(result);
            }
            file.println("\n--------------- VARIAVEIS ---------------");
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                file.print(f.getType() + " " + f.getName() + ": ");
                try {
                    Object object = f.get(this.patient);
                    if (object == null) {
                        file.print("null");
                    }
                    if (object instanceof String) {
                        file.print((String) object);
                    }
                    if (object instanceof String[]) {
                        String[] StringArray = (String[]) object;
                        file.print("[");
                        for (int a = 0; a < StringArray.length - 1; a++) {
                            file.print(StringArray[a] + " , ");
                        }
                        file.print(StringArray[StringArray.length - 1] + "]");
                        if (StringArray[1].equalsIgnoreCase("t") || StringArray[1].equalsIgnoreCase("f")) {
                            symptomsHolder = StringArray;
                        }
                    }
                    if (object instanceof Integer) {
                        file.print((Integer) object);
                        String[] possibleHolder = {"patientN", "Disease", "doenca", "linha"};
                        for (int a = 0; a < possibleHolder.length; a++) {
                            if (f.getName().equalsIgnoreCase(possibleHolder[a])) ;
                            diseaseHolder = (Integer) object;
                        }
                    }
                    file.println("");
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            try {
                file.println("\n--------------- DEBUG ---------------");
                file.println("diseaseHolder: " + diseaseHolder);
                file.print("symptomsHolder:\t");
                for (int a = 0; a < symptomsHolder.length - 1; a++) {
                    file.print(symptomsHolder[a]);
                }
                file.print("\nTableLine:\t\t");
                for (int a = 0; a < attributes.length - 1; a++) {
                    file.print(instances[diseaseHolder][a]);
                }
                for (int a = 0; a < attributes.length - 1; a++) {
                    if (!instances[diseaseHolder][a].equalsIgnoreCase(symptomsHolder[a])) {
                        file.println("Posicao " + a + "incongruente");
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Não foi possível achar diseaseHolder ou symptomsHolder");
            }
            file.close();
            file.println();
        } catch (IOException erro) {
            System.out.println("Impossível gravar em DebugLog.txt");
        }
    }

    public void debugDoctor() {
        try {
            PrintWriter file = new PrintWriter(new FileWriter("src/Equipiada/Components/Debugger/DebugLog/DebugLog.txt", true));
            file.println("\n================================ DOCTOR ================================");
            Class c = this.doctor.getClass();
            Method[] m = c.getDeclaredMethods();
            file.println("--------------- METODOS ---------------");
            for (int i = 0; i < m.length; i++) {
                String[] methodSplit = (m[i].toString()).split(" |\\$");
                String result = methodSplit[0] + " " + methodSplit[2];
                file.println(result);
            }
            file.println("\n--------------- VARIAVEIS ---------------");
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                file.print(f.getType() + " " + f.getName() + ": ");
                try {
                    Object object = f.get(this.doctor);
                    if (object == null) {
                        file.print("null");
                    }
                    if (object instanceof String) {
                        file.print((String) object);
                    }
                    if (object instanceof String[]) {
                        String[] StringArray = (String[]) object;
                        file.print("[");
                        for (int a = 0; a < StringArray.length - 1; a++) {
                            file.print(StringArray[a] + " , ");
                        }
                        file.print(StringArray[StringArray.length - 1] + "]");
                    }
                    if (object instanceof Integer) {
                        file.print((Integer) object);
                        String[] possibleHolder = {"patientN", "Disease", "doenca", "linha"};
                        for (int a = 0; a < possibleHolder.length; a++) {
                            if (f.getName().equalsIgnoreCase(possibleHolder[a])) ;
                            resultHolder = (Integer) object;
                        }
                    }
                    file.println("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                file.println("\n--------------- DEBUG ---------------");
                file.println("diseaseHolder: " + resultHolder);
                file.print("resultHolder:\t");
                file.print("\nTableLine:\t\t");
                for (int a = 0; a < attributes.length - 1; a++) {
                    file.print(instances[resultHolder][a]);
                }
                for (int a = 0; a < attributes.length - 1; a++) {
                    if (!instances[diseaseHolder][a].equalsIgnoreCase(symptomsHolder[a])) {
                        file.println("Posicao " + a + "incongruente");
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Não foi possível achar diseaseHolder ou symptomsHolder");
            }
            file.println();
            file.close();
        } catch (IOException erro) {
            System.out.println("Impossível gravar em DebugLog.txt");
        }
    }
}
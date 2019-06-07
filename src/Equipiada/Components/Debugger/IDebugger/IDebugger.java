package Equipiada.Components.Debugger.IDebugger;

import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Integer;
import java.io.FileWriter;

public interface IDebugger{
    public void connect(IDoctor doc, IPatient pat);
    // Conecta ao medico e ao doutor
    public Method[] debugPatient();
    // Retorna string de debug do paciente;
    public Method[] debugDoctor();
    // Retorna string de debug do doutor;
    //   public String[] debugDiagnosis();
    // Retorna string de debug da diagnose;
}
package Equipiada.Components.Debugger.IDebugger;

import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;

public interface IDebugger{
    public void connect(IDoctor doc, IPatient pat);
    // Conecta ao medico e ao doutor
    public void debugPatient();
    // Retorna string de debug do paciente;
    public void debugDoctor();
    // Retorna string de debug do doutor;
}
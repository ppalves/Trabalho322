package Equipiada.Components.Dialogue.IDialogue;

import Equipiada.Templates.IDoctor.IDoctor;
import Equipiada.Templates.IPatient.IPatient;

public interface IDialogue{
    public void connect(IPatient p, IDoctor d);
    public void additional(boolean additional);
    public void virose(boolean virose);
    public void identifiers(boolean identifiers);
    public void start();
    public String questions(String question);
    public String answers(String answer);

}







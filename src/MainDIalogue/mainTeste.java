package MainDIalogue;

import Equipiada.Templates.IPatient.*;
import Equipiada.Templates.Patient.*;
import tradutor.Translate;
import Equipiada.Templates.IDoctor.*;
import Equipiada.Templates.Doctor.*;
import Equipiada.Components.Dialogue.*;
import Equipiada.Components.Dialogue.Dialogue.Dialogue;


public class mainTeste {
    public static void main(String[] args){
	
    IPatient p = new Patient("Desu-chan");
    IDoctor d = new Doctor("Andreisu");
    
    Dialogue dia = new Dialogue();
    
    
    dia.connect(p,d);
    dia.additional(true);
    dia.virose(false);
    dia.identifiers(true);
    dia.start();
    System.out.println(dia.questions("paralysis"));
    System.out.println(dia.answer("yes"));
    System.out.println(Translate.translate(dia.diagnose("bacterial_infection")));
    }
}


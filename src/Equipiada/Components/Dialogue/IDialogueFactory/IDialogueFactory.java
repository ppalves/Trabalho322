package Equipiada.Components.Dialogue.IDialogueFactory;

import Equipiada.Components.Dialogue.Dialogue.Dialogue;
import Equipiada.Components.Dialogue.IDialogue.IDialogue;

public interface IDialogueFactory {
    public static Dialogue createDialogue(String path){
        return new Dialogue(path);
    }
}

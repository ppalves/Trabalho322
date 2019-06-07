package Equipiada.Components.Dialogue.DialogueFactory;

import Equipiada.Components.Dialogue.Dialogue.Dialogue;
import Equipiada.Components.Dialogue.IDialogue.IDialogue;
import Equipiada.Components.Dialogue.IDialogueFactory.IDialogueFactory;

public class DialogueFactory implements IDialogueFactory {
    public static Dialogue createDialogue(){
        return new Dialogue();
    }
}
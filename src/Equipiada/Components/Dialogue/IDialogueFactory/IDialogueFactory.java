package Equipiada.Components.Dialogue.IDialogueFactory;

import Equipiada.Components.Dialogue.Dialogue.Dialogue;

public interface IDialogueFactory {
    public static Dialogue createDialogue(){
        return new Dialogue();
    }
}

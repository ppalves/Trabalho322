package Equipiada.Components.Dialogue.IDialogue;

import Equipiada.Templates.ITreeDoctor.ITreeDoctor;
import Equipiada.Templates.IPatient.IPatient;

/**
 * Interface para o IDialogue - Equipiada MC322
 * leia mais em https://github.com/ppalves/Trabalho322
 *
 * @author Andreis Purim
 */
/* ****************************** Interface ****************************** */
public interface IDialogue{
    // Conecta com o paciente e doutor
    public void connect(IPatient p, ITreeDoctor d);
    // On ou Off dos diálogos adicionais
    public void additional(boolean additional);
    // On ou Off para transformar doenças em viroses
    public void virose(boolean virose);
    // On ou Off para identificadores e outras ações de teatro
    public void identifiers(boolean identifiers);
    // Para começar o diálogo
    public void start();
    // Envia a pergunta para o paciente
    public String questions(String question);
    // Envia a resposta para o doutor
    public String answer(String answer);
}







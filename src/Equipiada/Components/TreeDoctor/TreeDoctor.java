package Equipiada.Components.TreeDoctor;

import Equipiada.Templates.ITreeDoctor.ITreeDoctor;
import Equipiada.Templates.IResponder.IResponder;
import Equipiada.Templates.ITableProducer.ITableProducer;

/**
 * Classe do componente TreeDoctor da Equipiada, MC322.
 * Leia o readme em https://github.com/ppalves/Trabalho322
 *
 * @author Pedro Pupo
 * @version 1.0
 */

public class TreeDoctor implements ITreeDoctor{
    private String nome;
    private int patientN = 0;

    public TreeDoctor(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    private ITableProducer producer;
    private IResponder responder;

    public void connect(ITableProducer producer) {
        this.producer = producer;
    }

    public void connect(IResponder responder) {
        this.responder = responder;
    }

    public String askPatiente(String attribute){
        return responder.ask(attribute);
    }


}

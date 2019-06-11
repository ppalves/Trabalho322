package Equipiada.Templates.ITreeDoctor;

import Equipiada.Templates.IResponderReceptacle.IResponderReceptacle;
import Equipiada.Templates.ITableProducerReceptacle.ITableProducerReceptacle;

public interface ITreeDoctor extends IResponderReceptacle, ITableProducerReceptacle{
    public String getNome();
    public String askPatiente(String attribute);
}

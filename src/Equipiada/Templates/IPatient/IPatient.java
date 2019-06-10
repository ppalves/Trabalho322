package Equipiada.Templates.IPatient;

import Equipiada.Templates.IResponder.IResponder;
import Equipiada.Templates.ITableProducerReceptacle.ITableProducerReceptacle;

public interface IPatient extends IResponder, ITableProducerReceptacle {
	public String getNome();
}

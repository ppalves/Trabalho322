package Equipiada.Components.Statistic.IStatistic;

import Equipiada.Templates.ITableProducer.ITableProducer;
import Equipiada.Templates.ITableProducerReceptacle.ITableProducerReceptacle;

public interface IStatistic extends ITableProducerReceptacle {
    void connect(ITableProducer producer);

    String[][] findUnique();

    String[][] findFrequency();

    String[][] relativePercentage();

    String[][] absolutePercentage();

    String[][] diagnose(String symptom);

    String[][] diagnose(String[] symptoms);
}
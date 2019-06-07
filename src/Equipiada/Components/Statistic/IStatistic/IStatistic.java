package Equipiada.Components.Statistic.IStatistic;

import Equipiada.Templates.ITableProducer.ITableProducer;
import Equipiada.Templates.ITableProducerReceptacle.ITableProducerReceptacle;

/**
 * Interface IStatístic para o componente Statistic da Equipiada. MC322
 * Nota: As matrizes de Strings não são guardadas internamente na classe.
 * Guarde-as no programa principal se deseja utilizar frequentemente.
 *
 * @author Andreis Purim
 */
/* ******************************* Interface ******************************* */
public interface IStatistic extends ITableProducerReceptacle {
    // função de conexão, já inicia o tratamento
    void connect(ITableProducer producer);
    // Retorna uma matriz de doenças únicas e porcentagens
    String[][] findUnique();
    // Acha frequências
    String[][] findFrequency();
    // Acha frequências relativas e absolutas
    String[][] relativePercentage();
    // Acha frequências relativas e absolutas
    String[][] absolutePercentage();
    // Faz o diagnóstico de um sintoma
    String[][] diagnose(String symptom);
    // Diagonóstico de vários sintomas
    String[][] diagnose(String[] symptoms);
}
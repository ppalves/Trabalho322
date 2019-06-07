package Equipiada.Components.Statistic.IStatisticFactory;

import Equipiada.Components.Statistic.Statistic.Statistic;

/**
 * Interface para a Fábrica IStatistic
 */
public interface IStatisticFactory {
    // Cria statistic
    static Statistic createStatistic() {
        return new Statistic();
    }
}

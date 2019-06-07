package Equipiada.Components.Statistic.IStatisticFactory;

import Equipiada.Components.Statistic.Statistic.Statistic;

public interface IStatisticFactory {
    static Statistic createStatistic() {
        return new Statistic();
    }
}

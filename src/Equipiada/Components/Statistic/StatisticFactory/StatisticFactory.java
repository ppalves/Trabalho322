package Equipiada.Components.Statistic.StatisticFactory;

import Equipiada.Components.Statistic.IStatisticFactory.IStatisticFactory;
import Equipiada.Components.Statistic.Statistic.Statistic;

public class StatisticFactory implements IStatisticFactory {
    public static Statistic createStatistic() {
        return new Statistic();
    }
}

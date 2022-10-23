package hidenseek.model.statistics;

public interface StatisticSaver {

    void saveStatistic();
    void loadStatistic(Statistic<?> statistic);
}

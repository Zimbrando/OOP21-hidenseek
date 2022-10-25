package hidenseek.model.statistics;

public interface StatisticSaver {

    /**
     * Save statistics into file
     */
    void saveStatistic();
    
    /**
     * Load statistic from file
     * @param statistic
     */
    void loadStatistic(Statistic<?> statistic);
}

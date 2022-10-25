package hidenseek.model.statistics;

import java.util.Set;
import java.util.stream.Stream;

public interface StatisticsManager {
    
    /**
     * @return list of statistics
     */
    Set<Statistic<?>> getStatistics();

    /**
     * @return a list of statistic with specified ID
     * @param statisticID
     */
    Stream<Statistic<?>> getStatistic(String statisticID);

    /**
     * @return a list of statistic with specified ID and Tag
     * @param statisticID
     * @param statisticTag
     */
    Stream<Statistic<?>> getStatistic(String statisticID, String statisticTag);

    /**
     * Add statistic
     * @param statistic
     */
    void addStatistic(Statistic<?> statistic);

    /**
     * Remove statistic
     * @param statistic
     */
    void removeStatistic(Statistic<?> statistic);
    
}

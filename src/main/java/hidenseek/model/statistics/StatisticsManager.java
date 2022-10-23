package hidenseek.model.statistics;

import java.util.Set;
import java.util.stream.Stream;

public interface StatisticsManager {
    
    Set<Statistic<?>> getStatistics();

    Stream<Statistic<?>> getStatistic(String statisticID);

    Stream<Statistic<?>> getStatistic(String statisticID, String statisticTag);

    void addStatistic(Statistic<?> statistic);

    void removeStatistic(Statistic<?> statistic);
    
}

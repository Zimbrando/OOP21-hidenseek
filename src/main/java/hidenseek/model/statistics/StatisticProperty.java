package hidenseek.model.statistics;

public abstract class StatisticProperty<T> {
    protected abstract String XMLSerialize();
    protected abstract void XMLDeserialize(String value);
    
    protected abstract T getValue();
    protected abstract void setValue(T value);
    
    private StatisticSaver statisticSaver = null;

    /**
     * @return the statisticSaver
     */
    public StatisticSaver getStatisticSaver() {
        return statisticSaver;
    }

    /**
     * Set the statistic saver.
     * It'll be used every time the value changes
     * @param statisticSaver
     */
    public void setStatisticSaver(StatisticSaver statisticSaver) {
        this.statisticSaver = statisticSaver;
    }
    
    /**
     * Save the statistic into file
     */
    public void saveStatistics() {
        statisticSaver.saveStatistic();
    }
    
    
}

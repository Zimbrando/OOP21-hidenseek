package hidenseek.model.statistics;

public abstract class StatisticProperty<T> {
    protected abstract String XMLSerialize();
    protected abstract void XMLDeserialize(String value);
    
    protected abstract T getValue();
    protected abstract void setValue(T value);
    
    private StatisticSaver statisticSaver = null;

    public StatisticSaver getStatisticSaver() {
        return statisticSaver;
    }

    public void setStatisticSaver(StatisticSaver statisticSaver) {
        this.statisticSaver = statisticSaver;
    }
    
    public void saveStatistics() {
        statisticSaver.saveStatistic();
    }
    
}

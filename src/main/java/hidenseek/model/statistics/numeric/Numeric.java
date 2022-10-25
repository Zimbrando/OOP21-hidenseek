package hidenseek.model.statistics.numeric;

import hidenseek.model.statistics.StatisticProperty;

public class Numeric extends StatisticProperty<Integer> {

    private int number;
    public Numeric(final int number) {
        this.number = number;
    }
    
    @Override
    public String XMLSerialize() {
        return Integer.toString(number);
    }

    @Override
    public void XMLDeserialize(final String value) {
        number = Integer.parseInt(value);
    }

    @Override
    public Integer getValue() {
        return number;
    }

    @Override
    public void setValue(final Integer value) {
        number = value;
        saveStatistics();
    }
    
    public void increase(final Integer diff) {
        setValue(number+diff);
    }
    
    public void decrease(final Integer diff) {
        setValue(Math.max(0, number-diff));
    }
    
    @Override
    public String toString() {
        return Integer.toString(number);
    }
}

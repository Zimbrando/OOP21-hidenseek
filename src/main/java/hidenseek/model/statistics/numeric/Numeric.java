package hidenseek.model.statistics.numeric;

import hidenseek.model.statistics.StatisticProperty;

public class Numeric extends StatisticProperty<Integer> {

    private int _number;
    public Numeric(int number) {
        _number = number;
    }
    
    @Override
    public String XMLSerialize() {
        return Integer.toString(_number);
    }

    @Override
    public void XMLDeserialize(String value) {
        _number = Integer.parseInt(value);
    }

    @Override
    public Integer getValue() {
        return _number;
    }

    @Override
    public void setValue(Integer value) {
        _number = value;
        saveStatistics();
    }
    
    public void increase(Integer diff) {
        setValue(_number+diff);
    }
    
    public void decrease(Integer diff) {
        setValue(Math.max(0, _number-diff));
    }
}

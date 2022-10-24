package hidenseek.model.statistics.score;

import java.text.DecimalFormat;

import hidenseek.model.statistics.StatisticProperty;

public class Score extends StatisticProperty<Double> {

    private Double _score;
    public Score(Double score) {
        _score = score;
    }
    
    @Override
    public String XMLSerialize() {
        return Double.toString(_score);
    }

    @Override
    public void XMLDeserialize(String value) {
        _score = Double.parseDouble(value);
    }

    @Override
    public Double getValue() {
        return _score;
    }

    @Override
    public void setValue(Double value) {
        if(value < 0) {
            value = 0.0;
        }
        if(value > 5) {
            value = 5.0;
        }
        _score = value;
        saveStatistics();
    }
    
    @Override
    public String toString() {
        return new DecimalFormat("0.0").format(_score) + "/5";
    }
}

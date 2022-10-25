package hidenseek.model.statistics.score;

import java.text.DecimalFormat;

import hidenseek.model.statistics.StatisticProperty;

public class Score extends StatisticProperty<Double> {

    private Double score;
    public Score(final Double score) {
        this.score = score;
    }
    
    @Override
    public String XMLSerialize() {
        return Double.toString(score);
    }

    @Override
    public void XMLDeserialize(final String value) {
        score = Double.parseDouble(value);
    }

    @Override
    public Double getValue() {
        return score;
    }

    @Override
    public void setValue(Double value) {
        if(value < 0) {
            value = 0.0;
        }
        if(value > 5) {
            value = 5.0;
        }
        score = value;
        saveStatistics();
    }
    
    @Override
    public String toString() {
        return new DecimalFormat("0.0").format(score) + "/5";
    }
}

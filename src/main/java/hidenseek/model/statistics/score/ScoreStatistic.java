package hidenseek.model.statistics.score;

import org.w3c.dom.Element;

import hidenseek.model.statistics.AbstractStatistic;

public class ScoreStatistic extends AbstractStatistic<Score> {
    
    public ScoreStatistic(String name, String tag, String title, Element element) {
        super(name, tag, title, new Score(0.0), element);
    }
    
    public ScoreStatistic(String name, String tag, String title) {
        this(name, tag, title, null);
    }

    /**
     * @return the value of the statistic
     */
    public double getValue() {
        return getProperty().getValue();
    }

}

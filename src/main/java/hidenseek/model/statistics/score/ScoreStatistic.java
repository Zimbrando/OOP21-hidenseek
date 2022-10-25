package hidenseek.model.statistics.score;

import org.w3c.dom.Element;

import hidenseek.model.statistics.AbstractStatistic;

public class ScoreStatistic extends AbstractStatistic<Score> {
    
    public ScoreStatistic(final String name, final String tag, final String title, final Element element) {
        super(name, tag, title, new Score(0.0), element);
    }
    
    public ScoreStatistic(final String name, final String tag, final String title) {
        this(name, tag, title, null);
    }

    /**
     * @return the value of the statistic
     */
    public double getValue() {
        return getProperty().getValue();
    }

}

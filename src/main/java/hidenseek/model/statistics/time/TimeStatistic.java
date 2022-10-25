package hidenseek.model.statistics.time;

import org.w3c.dom.Element;
import hidenseek.model.statistics.AbstractStatistic;

public class TimeStatistic extends AbstractStatistic<Time> {
    
    public TimeStatistic(final String name, final String tag, final String title, final Element element) {
        super(name, tag, title, new Time(0), element);
    }
    
    public TimeStatistic(final String name, final String tag, final String title) {
        this(name, tag, title, null);
    }

    /**
     * @return the value of the statistic
     */
    public String getValue() {
        return getProperty().toString();
    }

}

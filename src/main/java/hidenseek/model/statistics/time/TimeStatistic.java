package hidenseek.model.statistics.time;

import org.w3c.dom.Element;

import hidenseek.model.statistics.StatisticImpl;

public class TimeStatistic extends StatisticImpl<Time> {
    
    public TimeStatistic(String name, String tag, String title, Element element) {
        super(name, tag, title, new Time(0), element);
    }
    
    public TimeStatistic(String name, String tag, String title) {
        this(name, tag, title, null);
    }

}

package hidenseek.model.statistics.numeric;

import org.w3c.dom.Element;
import hidenseek.model.statistics.AbstractStatistic;

public class NumericStatistic extends AbstractStatistic<Numeric> {
    
    private final String units;
    
    public NumericStatistic(final String name, final String tag, final String title, final String units, final Element element) {
        super(name, tag, title, new Numeric(0), element);
        
        this.units = units;
    }
    
    public NumericStatistic(final String name, final String tag, final String title, final String units) {
        this(name, tag, title, units, null);
    }
    
    public NumericStatistic(final String name, final String tag, final String title) {
        this(name, tag, title, "", null);
    }

    /**
     * @return the value of the statistic
     */
    public String getValue() {
        return getProperty().toString();
    }
    
    /**
     * @return the measurement unit of this statistic (e.g. "times", "%", ...)
     */
    public String getUnits() {
        return units;
    }


}

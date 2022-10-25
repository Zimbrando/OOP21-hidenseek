package hidenseek.model.statistics;

import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class AbstractStatistic<T extends StatisticProperty<?>> implements Statistic<T> {
    private final String name;
    private final String tag;
    private final String title;
    private final T property;
    
    public AbstractStatistic(final String name, final String tag, final String title, final T property) {
        this(name, tag, title, property, null);
    }
    
    public AbstractStatistic(final String name, final String tag, final String title, final T property, final Element element) {
        this.name = name;
        this.tag = tag;
        this.title = title;
        this.property = property;
        
        if(element != null) {
            XMLDeserialize(element);
        }
    }

    @Override
    public Element XMLSerialize(final Document document) {
        final Element element = document.createElement(getClass().getName());
        element.setAttribute("name", name);
        element.setAttribute("tag", tag);
        element.setAttribute("value", getProperty().XMLSerialize());
        return element;
    }

    @Override
    public Boolean XMLDeserialize(final Element element) {
        if(!element.getTagName().equals(getClass().getName()) || !element.getAttribute("name").equals(name) || !element.getAttribute("tag").equals(tag)) {
            return false;
        }
        if(!element.hasAttribute("value")) {
            return false;
        }
        getProperty().XMLDeserialize(element.getAttribute("value"));
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public T getProperty() {
        return property;
    }

    @Override
    public void setStatisticSaver(final StatisticSaver statisticSaver) {
        getProperty().setStatisticSaver(statisticSaver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AbstractStatistic<?> other = (AbstractStatistic<?>) obj;
        return Objects.equals(name, other.name) && Objects.equals(tag, other.tag);
    }
}

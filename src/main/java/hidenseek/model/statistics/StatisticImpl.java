package hidenseek.model.statistics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StatisticImpl<T extends StatisticProperty<?>> implements Statistic<T> {
    private final String name;
    private final String tag;
    private final String title;
    private final T property;
    
    public StatisticImpl(String name, String tag, String title, T property) {
        this(name, tag, title, property, null);
    }
    
    public StatisticImpl(String name, String tag, String title, T property, Element element) {
        this.name = name;
        this.tag = tag;
        this.title = title;
        this.property = property;
        
        if(element != null) {
            XMLDeserialize(element);
        }
    }

    @Override
    public Element XMLSerialize(Document document) {
        Element element = document.createElement(getClass().getName());
        element.setAttribute("id", name);
        element.setAttribute("tag", tag);
        element.setAttribute("value", getProperty().XMLSerialize());
        return element;
    }

    @Override
    public Boolean XMLDeserialize(Element element) {
        if(!element.getTagName().equals(getClass().getName()) || !element.getAttribute("tag").equals(tag)) {
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
    public String getValue() {
        return "A" + property.toString();
    }

    @Override
    public void setStatisticSaver(StatisticSaver statisticSaver) {
        getProperty().setStatisticSaver(statisticSaver);
    }
}

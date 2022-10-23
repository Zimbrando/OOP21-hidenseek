package hidenseek.model.statistics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Statistic<T extends StatisticProperty<?>> {
    
    Element XMLSerialize(Document document);
    
    Boolean XMLDeserialize(Element element);
    
    String getName();
    
    String getTag();
    
    String getTitle();
    
    T getProperty();
    
    void setStatisticSaver(StatisticSaver statisticSaver);

}

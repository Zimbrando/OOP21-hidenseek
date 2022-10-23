package hidenseek.model.statistics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface Statistic<T extends StatisticProperty<?>> {
    
    Element XMLSerialize(Document document);
    
    Boolean XMLDeserialize(Element element);
    
    String getName();
    
    String getTag();
    
    String getTitle();
    
    String getValue();
    
    T getProperty();
    
    void setStatisticSaver(StatisticSaver statisticSaver);

}

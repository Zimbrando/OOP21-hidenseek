package hidenseek.model.statistics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Statistic<T extends StatisticProperty<?>> {
    
    /**
     * @return the statistic serialized as XML Element
     */
    Element XMLSerialize(Document document);
    
    /**
     * Deserialize the statistic reading data from XML Element.
     * If element is not compatible with the component deserialization will have no effect.
     * @return the return of deserialization
     */
    Boolean XMLDeserialize(Element element);
    
    /**
     * @return the statistic name
     */
    String getName();
    
    /**
     * @return the statistic tag
     */
    String getTag();
    
    /**
     * @return the statistic title
     */
    String getTitle();
    
    /**
     * @return the statistic associated property
     */
    T getProperty();
    
    /**
     * Set the statistic saver.
     * It'll be used every time the value changes
     * @param statisticSaver
     */
    void setStatisticSaver(StatisticSaver statisticSaver);

}

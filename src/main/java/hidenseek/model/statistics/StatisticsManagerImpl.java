package hidenseek.model.statistics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StatisticsManagerImpl implements StatisticsManager, StatisticSaver {

    private final Set<Statistic<?>> statistics = new LinkedHashSet<Statistic<?>>();
    private final Element rootSaverXML; 
    
    public StatisticsManagerImpl() {
        Element _rootSaverXML = null;
        try {
            final File inputFile = new File("game_data.xml");
            if(inputFile.exists()) {
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
                _rootSaverXML = doc.getDocumentElement();
                _rootSaverXML.normalize();
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            
        }
        this.rootSaverXML = _rootSaverXML;
    }

    
    @Override
    public Set<Statistic<?>> getStatistics() {
        return statistics;
    }

    @Override
    public Stream<Statistic<?>> getStatistic(String statisticID) {
        return statistics.stream()
                .filter(x -> x.getName().equals(statisticID));
    }

    @Override
    public Stream<Statistic<?>> getStatistic(String statisticID, String statisticTag) {
        return statistics.stream()
                .filter(x -> x.getName().equals(statisticID))
                .filter(x -> x.getTag().equals(statisticTag));
    }

    @Override
    public void addStatistic(Statistic<?> statistic) {
        statistics.add(statistic);
        statistic.getProperty().setStatisticSaver(this);
        loadStatistic(statistic);
    }

    @Override
    public void removeStatistic(Statistic<?> statistic) {
        statistics.remove(statistic);
    }


    @Override
    public void saveStatistic() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("gamedata");
            doc.appendChild(rootElement);
            
            for(Statistic<?> statistic : getStatistics()) {
                rootElement.appendChild(statistic.XMLSerialize(doc));
            }

            FileOutputStream output = new FileOutputStream("game_data.xml");
            writeXml(doc, output);
        
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadStatistic(Statistic<?> statistic) {
        if(rootSaverXML == null) {
            return;
        }
        NodeList chilNodes = rootSaverXML.getChildNodes();
        for(int i=0; i<chilNodes.getLength(); i++) {
            Node childNode = chilNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                statistic.XMLDeserialize((Element)childNode);
            }
            
        }
    }
    
    private static void writeXml(Document doc, OutputStream output) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        // initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(output);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        //String xmlString = result.getWriter().toString();
       // System.out.println(xmlString);
        
        
        
        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
        //Transformer transformer = transformerFactory.newTransformer();
        //DOMSource source = new DOMSource(doc);
        //StreamResult result = new StreamResult(output);
        
        //transformer.transform(source, result);
        
    }
}

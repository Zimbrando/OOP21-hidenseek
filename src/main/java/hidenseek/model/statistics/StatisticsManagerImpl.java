package hidenseek.model.statistics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import net.harawata.appdirs.AppDirsFactory;

public class StatisticsManagerImpl implements StatisticsManager, StatisticSaver {

    private final Set<Statistic<?>> statistics = new LinkedHashSet<Statistic<?>>();
    private final Element rootSaverXML; 
    private static final String FILE_PATH = AppDirsFactory.getInstance().getUserDataDir("stats", "", "hidenseek");
    private static final String FILE_NAME = "game_stats.xml";
    
    public StatisticsManagerImpl() {
        Element _rootSaverXML = null;
        try {
            Files.createDirectories(Paths.get(FILE_PATH));
            final File inputFile = new File(FILE_PATH + FILE_NAME);
            if (inputFile.exists()) {
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
                _rootSaverXML = doc.getDocumentElement();
                _rootSaverXML.normalize();
            }
            
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        this.rootSaverXML = _rootSaverXML;
    }

    
    @Override
    public Set<Statistic<?>> getStatistics() {
        return statistics;
    }

    @Override
    public Stream<Statistic<?>> getStatistic(final String statisticID) {
        return statistics.stream()
                .filter(x -> x.getName().equals(statisticID));
    }

    @Override
    public Stream<Statistic<?>> getStatistic(final String statisticID, final String statisticTag) {
        return statistics.stream()
                .filter(x -> x.getName().equals(statisticID))
                .filter(x -> x.getTag().equals(statisticTag));
    }

    @Override
    public void addStatistic(final Statistic<?> statistic) {
        if(statistics.contains(statistic)) {
            return;
        }
        statistics.add(statistic);
        statistic.getProperty().setStatisticSaver(this);
        loadStatistic(statistic);
    }

    @Override
    public void removeStatistic(final Statistic<?> statistic) {
        statistics.remove(statistic);
    }


    @Override
    public void saveStatistic() {
        try {
            final DocumentBuilder docBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document doc = docBuilder.newDocument();
            final Element rootElement = doc.createElement("gamedata");
            doc.appendChild(rootElement);
            
            for(final Statistic<?> statistic : getStatistics()) {
                rootElement.appendChild(statistic.XMLSerialize(doc));
            }

            final FileOutputStream output = new FileOutputStream(FILE_PATH + FILE_NAME);
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
    public void loadStatistic(final Statistic<?> statistic) {
        if(rootSaverXML == null) {
            return;
        }
        
        final NodeList chilNodes = rootSaverXML.getChildNodes();
        for(int i=0; i<chilNodes.getLength(); i++) {
            final Node childNode = chilNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                statistic.XMLDeserialize((Element)childNode);
            }
        }
    }
    
    private static void writeXml(final Document doc, final OutputStream output) throws TransformerException {
        final Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        final StreamResult result = new StreamResult(output);
        transformer.transform(new DOMSource(doc), result);
    }
}

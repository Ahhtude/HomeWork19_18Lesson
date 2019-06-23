import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomParserDemo {

    public static Document document;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        InputStream is = DomParserDemo.class.getClassLoader().getResourceAsStream("Test.xml");
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        document = documentBuilder.parse(is);


        Element rootElement = document.getDocumentElement();
        List<Event> list = printXML(rootElement.getChildNodes());

        for (int i=0; i<list.size();i++){
            System.out.println(list.get(i).getEvent_id()+ " " + list.get(i).getEvent_date()
                    + " " + list.get(i).getParameter().getPriority() + " " + list.get(i).getParameter().getLog_level()
            + " " + list.get(i).getParameter().getSource());
        }

        }

    public static List<Event> printXML(NodeList childs) {
        List<Event> listEvents = new ArrayList<>(5);
        for (int i=0;i<5;i++){
            listEvents.add(new Event());
            listEvents.get(i).setParameter(new EventParameter());
        }

        int index =0;
        for (int i =0; i<childs.getLength(); i++){
            Node node = childs.item(i);
            if (node instanceof Element && node.getNodeName().equals("event")) {
                Element element = (Element) node;
                NodeList nodeList = element.getElementsByTagName("event_id");
                listEvents.get(index).setEvent_id(nodeList.item(0).getTextContent());

                nodeList = element.getElementsByTagName("event_date");
                listEvents.get(index).setEvent_date(nodeList.item(0).getTextContent());

                nodeList = element.getElementsByTagName("priority");
                listEvents.get(index).getParameter().setPriority(Integer.parseInt(nodeList.item(0).getTextContent()));

                nodeList = element.getElementsByTagName("log_level");
                listEvents.get(index).getParameter().setLog_level(nodeList.item(0).getTextContent());

                nodeList = element.getElementsByTagName("source");
                listEvents.get(index++).getParameter().setSource(nodeList.item(0).getTextContent());
            }


        }
        return listEvents;
    }
}




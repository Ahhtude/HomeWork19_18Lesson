import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getEvent_id());
            System.out.println(list.get(i).getEvent_date());
            System.out.println(list.get(i).getParameter().getPriority());
            System.out.println(list.get(i).getParameter().getLog_level());
            System.out.println(list.get(i).getParameter().getSource());
            System.out.println();
        }


    }

    public static List<Event> printXML(NodeList childs) {
        List<Event> listEvents = new ArrayList<>(5);
        List<EventParameter> parameters = new ArrayList<>();


        NodeList events = document.getElementsByTagName("event");
        for (int i = 0; i < events.getLength(); i++) {
            listEvents.add(new Event());
            parameters.add(new EventParameter());
        }

        NodeList event_date_list = document.getElementsByTagName("event_date");
        for (int i = 0; i < event_date_list.getLength(); i++) {
            listEvents.get(i).setEvent_date(event_date_list.item(i).getTextContent());
        }

        NodeList event_id_list = document.getElementsByTagName("event_id");
        for (int i = 0; i < event_id_list.getLength(); i++) {
            listEvents.get(i).setEvent_id(event_id_list.item(i).getTextContent());
        }

        NodeList log_levelList = document.getElementsByTagName("log_level");
        for (int i = 0; i < log_levelList.getLength(); i++) {
            parameters.get(i).setLog_level(log_levelList.item(i).getTextContent());
        }

        NodeList priorityList = document.getElementsByTagName("priority");
        for (int i = 0; i < priorityList.getLength(); i++) {
            parameters.get(i).setPriority(Integer.parseInt(priorityList.item(i).getTextContent()));
        }

        NodeList sourceList = document.getElementsByTagName("source");
        for (int i = 0; i < sourceList.getLength(); i++) {
            parameters.get(i).setSource(sourceList.item(i).getTextContent());
            listEvents.get(i).setParameter(parameters.get(i));
        }

        return listEvents;
    }
}




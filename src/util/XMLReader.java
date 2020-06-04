package util;

import entity.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {
    public static List<Student> getAll(String filexml) throws ParserConfigurationException, IOException, SAXException {
        List<Student> ds = null;
        DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuil = docFac.newDocumentBuilder();
        Document doc = docBuil.parse(filexml);
        Element root = doc.getDocumentElement();
        NodeList listChildren = root.getElementsByTagName("student");
        if (listChildren.getLength() > 0) {
            ds = new ArrayList<Student>();
            Student student;
            Node node;
            String name;
            byte age;
            String subject;
            boolean gender;
            for(int i = 0; i < listChildren.getLength(); i++){
                node = listChildren.item(i);
                NodeList nodeChildrens = node.getChildNodes();
                name = nodeChildrens.item(1).getTextContent();
                age = Byte.parseByte(nodeChildrens.item(3).getTextContent());
                subject = nodeChildrens.item(5).getTextContent();
                gender = nodeChildrens.item(7).getTextContent().compareToIgnoreCase("Male") == 0 ? true : false;
                student = new Student(name, age, subject, gender);
                ds.add(student);
            }
        }
        return ds;
    }
}

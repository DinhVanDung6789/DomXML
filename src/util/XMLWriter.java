package util;

import entity.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XMLWriter {
    public static void write(String filexml, Student student) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        File file = new File(filexml);
        boolean exitFile = false;
        if (!file.exists() || !file.isFile()){
            file = new File("filexml/studentsw.xml");
            exitFile = !file.createNewFile();
        }
        DocumentBuilderFactory docFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuil = docFac.newDocumentBuilder();
        Document doc = docBuil.newDocument();
        Element root = doc.getDocumentElement();
        if (root == null) {
            root = doc.createElement("students");
            doc.appendChild(root);
        }
        Element nodeName = doc.createElement("name");
        nodeName.setTextContent(student.getName());
        Element nodeAge = doc.createElement("age");
        nodeAge.setTextContent(String.valueOf(student.getAge()));
        Element nodeSubject = doc.createElement("subject");
        nodeSubject.setTextContent(student.getSubject());
        Element nodeGender = doc.createElement("gender");
        nodeGender.setTextContent(student.isGender() ? "Male" : "Female");

        Element elementChildren = doc.createElement("student");
        elementChildren.appendChild(nodeName);
        elementChildren.appendChild(nodeAge);
        elementChildren.appendChild(nodeSubject);
        elementChildren.appendChild(nodeGender);

        root.appendChild(elementChildren);
        if (exitFile){
            DOMSource dom = new DOMSource(root);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.transform(dom, new StreamResult(file));
        }
    }
}

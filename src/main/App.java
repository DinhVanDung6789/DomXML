package main;

import entity.Student;
import org.xml.sax.SAXException;
import util.XMLReader;
import util.XMLWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            List<Student> ds = XMLReader.getAll("filexml/students.xml");
            System.out.println(ds);
            try {
                XMLWriter.write("", ds.get(0));
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}

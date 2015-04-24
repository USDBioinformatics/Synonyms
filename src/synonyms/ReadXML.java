/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synonyms;

/**
 *
 * @author Mathialakan.Thavappi
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadXML {
    
    public ArrayList<String> read(String xmlString, String element) {
        ArrayList<String> synonymList = new ArrayList<String>();
        try {
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new InputSource(new StringReader(xmlString.trim()))); //new ByteArrayInputStream(xmlString)));

            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            
            NodeList listOfNodes = doc.getElementsByTagName(element);
            int totalNodes = listOfNodes.getLength();
            System.out.println("Total no of element : " + totalNodes);
            int noSynonyms = 0;
            
            for (int i = 0; i < listOfNodes.getLength(); i++) {
                Node node = listOfNodes.item(i);
                //System.out.println(" synonyms : " + node.getTextContent().trim());
                System.out.println(" synonyms parent: " + node.getParentNode().getNodeName().trim());
                if (node.getParentNode().getNodeName().trim().equalsIgnoreCase("Compound")) {
                    noSynonyms++;
                    synonymList.add(node.getTextContent().trim());
                }
            }
            System.out.println("Total no of synonyms : " + noSynonyms);
            
        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line "
                    + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
            
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return synonymList;
    }
    
    public void readSAX(String xmlStrng, String element) {
        
        try {
            String tagContent = null;
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(
                    ClassLoader.getSystemResourceAsStream(xmlStrng));
            
            while (reader.hasNext()) {
                int event = reader.next();
                
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (element.equals(reader.getLocalName())) {
                            
                            tagContent = "";
                        }
                        break;
                    
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        System.out.println("e :" + tagContent);
                        break;
                }
                
            }
        } catch (XMLStreamException err) {
            System.out.println(" " + err.getMessage());
            
        }
    }

    private String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();        
        DocumentBuilder builder;        
        try {            
            builder = factory.newDocumentBuilder();            
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e) {            
            e.printStackTrace();            
        }
        return null;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package synonyms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import javax.xml.stream.XMLStreamException;
import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;

/**
 *
 * @author Mathialakan.Thavappi
 */
public class SynonymWeb {

    public SBMLDocument getDocument(String url) {
        SBMLDocument sBMLDocument = null;
        try {
            sBMLDocument = read(new URL(url).openStream());
            //System.out.println("json: "+json);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sBMLDocument;
    }

    public String getString(String url) {
        String content = "";
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String line;
            while ((line = rd.readLine()) != null) {
                content += "\n" + line;
            }
            //System.out.println("json: " + content);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public SBMLDocument read(InputStream inputStream) {
        try {
            return SBMLReader.read(inputStream);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (SBMLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SBMLDocument readXML(String string) {
        try {
            SBMLReader sBMLReader = new SBMLReader();
            return sBMLReader.readSBMLFromString(string);
        } catch (javax.xml.stream.XMLStreamException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    public SBMLDocument read(String string) {
        try {
            return JSBML.readSBMLFromFile(string);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (SBMLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

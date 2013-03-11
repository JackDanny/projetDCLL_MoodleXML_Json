package jsontoxml.xmlWriter;

import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Implementation de XmlWriter.
 * Génère le XML
 * */
public class XmlWriterImpl implements XmlWriter {

    /**
     * Racine du document XML.
     * */
     private Element root = new Element("quiz");

     /**
      * Document XML.
      * */
     private org.jdom2.Document document = new Document(root);


     /**
      * Write a MOODLE XML file to a {@link JSONObject}.
      *
      * @param oneQuestion : a single {@link JSONObject} of Moodle question
      * @param nameXmlFileOut : the name of XML output file
      *
      * */
     public final void writeXmlToJson(final JSONObject oneQuestion
             , final String nameXmlFileOut) {
         buildXML(oneQuestion);
         save(nameXmlFileOut); //save the xml document object
     }


     /**
      * Write a MOODLE XML file to a {@link JSONArray}.
      *
      * @param tabQuestions : a {@link JSONArray} of Moodle questions
      * <br> the {@link JSONArray} is ordered </br>
      * @param nameXmlFileOut : the name of XML output file
      *
      * */
     public final void writeXmlToJson(final JSONArray tabQuestions
             , final String nameXmlFileOut) {
         for (int i = 0; i < tabQuestions.length(); ++i) {
             try {
                 JSONObject quesObj = tabQuestions.getJSONObject(i);
                 buildXML(quesObj);
             } catch (Exception e) {
                 e.printStackTrace();
             }
        }
        save(nameXmlFileOut); //save the xml document object
    }

/**
 * Ajoute à {@link root} les éléments XML correspondant à l'objet JSON.
 * @param quesObj : l'objet JSON
 * */
    private void buildXML(final JSONObject quesObj) {
        GenXML generator = new GenXML();
        generator.addElments(quesObj);
        root.addContent(generator.getCommonTags());
    }

/**
 * Sauve le {@link document} dans le fichier.
 * @param file : le nom du fichier
 * */
    private void save(final String file) {
        try {
            System.out.println("ee save");
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(file));
          } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


}


package xmlparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nomenclature.QuestionType_enum;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


/**
 * Implementation of XmlParser
 * Parse XMl Moodel file.
 * */
public class XmlParserImpl implements XmlParser {


/**
 * @param fileName the XML file name
 * @return an Element list. Element : Jdom Type.
 * Each Element is a "Moodle question" implemented.
 * ie the attribute "type" of question is define like
 * implemented in QuestionType_enum.
 * */
public final List<Element> parser(final String fileName) {
org.jdom2.Document document;
Element racine;
SAXBuilder sxb = new SAXBuilder();
  document = null;
  try {
     document = sxb.build(new File(fileName));
  } catch (Exception e) {
  e.printStackTrace(); //TODO gestion erreur
  }

  racine = document.getRootElement();
  return getQestionList(racine);
}

/**
 * @param root : the root element of Moodel xml document
 * @return an Element list. Element : Jdom Type.
 * Each Element is a "Moodle question" implemented.
 * ie the attribute "type" of question is define like
 * implemented in QuestionType_enum.
 * */
private static List<Element> getQestionList(final Element root) {
   QuestionType_enum qTypeEnum;
   List<Element> listQuestion = root.getChildren("question");
   List<Element> listeQuestion = new ArrayList<Element>();
   Iterator<Element> i = listQuestion.iterator();
   while (i.hasNext()) {
     Element courant = (Element) i.next();
     qTypeEnum = QuestionType_enum.valueOf(courant.getAttributeValue("type"));
     if (qTypeEnum.isImplemented()) {
        System.out.println(qTypeEnum.name() + " traité");
        //List<Element> le= courant.getChildren();
    	 if (!listeQuestion.add(courant)) {
             throw new Error("Error in list add");
    	 }
     } else {
    	 System.out.println("Les questions de type " + qTypeEnum.name()
    			 + " ne peuvent être traités.");
         }
      }
      return listeQuestion;
}


}

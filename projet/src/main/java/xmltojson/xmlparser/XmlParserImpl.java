package xmltojson.xmlparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nomenclature.QuestionsKnown;

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
        List<Element> listQuestion = root.getChildren("question");
        List<Element> listeQuestion = new ArrayList<Element>();
        Iterator<Element> i = listQuestion.iterator();
        QuestionsKnown qk = new QuestionsKnown();
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            String type = courant.getAttributeValue("type");
            if (!qk.isKnownQuestion(type)) {
                System.out.println("Les questions de type " + type
                        + " sont inconnues.\nLe Json Writer génèrera "
                        + "la question.\nMais le XML Writer ne pourat "
                        + "pas gérer les attribus de ce json");
            }
            if (!listeQuestion.add(courant)) {
                throw new Error("Error in list add");
            }
        }
        return listeQuestion;
    }


}
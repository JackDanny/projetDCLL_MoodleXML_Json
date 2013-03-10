package xmltojson.xmlparser;

import java.util.List;

import org.jdom2.Element;

/**
 * XML parser Interface.
 * */
public interface XmlParser {

/**
* @param fileName the XML file name
 * @return an Element list. Element : Jdom Type.
 * Each Element is a "Moodle question" implemented.
 * ie the attribute "type" of question is define like
 * implemented in QuestionType_enum.
 * */

List<Element> parser(final String fileName);

}

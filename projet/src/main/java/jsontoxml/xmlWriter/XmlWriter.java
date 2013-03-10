package jsontoxml.xmlWriter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author florent
 *
 * Write a MOODLE XML to Json
 *
 * */

public interface XmlWriter {

/**
     * Write a MOODLE XML file to a {@link JSONArray}.
     *
	 * @param tabQuestions : a {@link JSONArray} of Moodle questions
	 * <br> the {@link JSONArray} is ordered </br>
	 * @param nameXmlFileOut : the name of XML output file
	 *
	 * */
	void writeXmlToJson(JSONArray tabQuestions, String nameXmlFileOut);

	/**
	 * Write a MOODLE XML file to a {@link JSONObject}.
	 *
	 * @param oneQuestion : a single {@link JSONObject} of Moodle question
	 * @param nameXmlFileOut : the name of XML output file
	 *
	 * */
	void writeXmlToJson(JSONObject oneQuestion, String nameXmlFileOut);

}

package jsonToXml.xmlWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public interface XmlWriter {

	public void writeXmlToJson(JSONArray tabQuestions, String nameXmlFileOut);

}

package jsontoxml.xmlWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public interface XmlWriter {

    public void writeXmlToJson(JSONObject oneQuestion, String nameXmlFileOut);
    
    public void writeXmlToJson(JSONArray tab,  String nameXmlFileOut);
    
}


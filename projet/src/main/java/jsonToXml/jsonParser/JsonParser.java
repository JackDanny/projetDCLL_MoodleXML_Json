package jsonToXml.jsonParser;

import org.json.JSONArray;

public interface JsonParser {

/**
 * @param filename : Json filename
 * 
 * @return a {@link JSONArray} of questions
 * */
	public JSONArray parser(String filename);
	
}

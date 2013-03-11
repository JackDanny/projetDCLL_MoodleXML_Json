package jsontoxml.jsonParser;

import org.json.JSONArray;

public interface JsonParser {

/**
 * @param filename : Json filename
 * 
 * @return a {@link JSONArray} of questions
 * */
	public void parser(String filename);
	
}

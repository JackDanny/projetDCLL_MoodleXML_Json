package jsonToXml.jsonParser;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class JsonParserImpl {

	public void lecture(){
		Reader reader= null;
		JSONObject o = null;
		try {
			reader = new FileReader("");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		JSONTokener jsonT = new JSONTokener(reader);

		try {
			o = new  JSONObject(jsonT);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		o.get(key);
		
		
		
		
		
		
	}
	
}

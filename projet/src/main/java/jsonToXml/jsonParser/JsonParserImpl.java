package jsonToXml.jsonParser;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class JsonParserImpl implements JsonParser {

	public static void main(String[] args) {
		
		 JsonParserImpl i = new JsonParserImpl();
		
		//i.lecture();
		i.parser("src/test/resources/user.json");
		//i.parser(args[0]);
		 
	}
	
	public void lecture(){
		Reader reader= null;
		JSONObject o = null;
		JSONArray i = null;

		
		try {
			reader = new FileReader("src/test/resources/jsonMoodle.json");
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
		
		JSONObject t= null;
		try {
			// t= o.get("menu");
			 t = o.getJSONObject("quiz");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray t2 = null;
		
		try {
			t2 = t.getJSONArray("question");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			System.out.println(i.get(0).toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public JSONArray parser(String filename) {
		// TODO Auto-generated method stub
		Reader reader= null;
		JSONObject o = null;
		JSONArray i = null;
		JSONObject o2 = null;
		
		try {
			reader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		JSONTokener jsonT = new JSONTokener(reader);
		try {
			o = new JSONObject(jsonT);
			o = o.getJSONObject("menu");
			o = o.getJSONObject("popup");
			i = o.getJSONArray("menuitem");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			
		e.printStackTrace();
		}
		
		//System.out.println(i);
		return i;
	}
	
}

package xmlToJson.traducteurJson;


import java.sql.Struct;
import java.util.ArrayList;

import net.sf.json.JSONObject;

/**
 * 
 * @author nawal
 *
 */
public class test {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws Exception {
		
    	JSONObject json = new JSONObject();  	    	
    	JsonGen j = new JsonGen();
    	j.baliseSimple(json, "menu");
    	ArrayList<ArrayList<String>> listeAttributs= new ArrayList<ArrayList<String>>();
    	ArrayList<String> l = new ArrayList<>();    	
    	l.add("11");
    	l.add("12");
    	listeAttributs.add(l);
    	j.attribute(json, listeAttributs); 
    	System.out.print(json.toString(2));

    	
    }

}

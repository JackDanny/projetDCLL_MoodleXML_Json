package jsontoxml.jsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import jsontoxml.xmlWriter.XmlWriter;
import jsontoxml.xmlWriter.XmlWriterImpl;
import main.Main;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @class Implementation du Parser JSON
 * */

public class JsonParserImpl implements JsonParser {

	public static void main(String[] args) {
		/**
		 * @main 
		 * En entrée un fichier Json 
		 * En sortie un fichier XML 
		 **/
		 JsonParserImpl i = new JsonParserImpl();
		 i.parser("src/test/resources/USE_TrueFalse_AUTOGEN.json");
	}
	

	
	public void parser(String filename) {
		/**
		 * @methode Code du parser JSON
		 * */
		Reader reader= null;
		JSONObject o = null;
		JSONArray i = null;
		JSONObject o2 = null;
		XmlWriter xmlWriter = new XmlWriterImpl();
		
		try {
			reader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		JSONTokener jsonT = new JSONTokener(reader);
		try {
			o = new JSONObject(jsonT);
			o = o.getJSONObject("quiz");
			/** 
			 * Test pour différencier entre une question (type object) 
			 * de plusieurs question( type array )
			 * 
			 **/
			if(o.optJSONArray("question")==null){
				o = o.getJSONObject("question");
				xmlWriter.writeXmlToJson(o, renomeFile(filename));
			}
			else{
				i = o.getJSONArray("question");
                xmlWriter.writeXmlToJson(i, renomeFile(filename));
			}
		} catch (JSONException e) {
		    e.printStackTrace();
		}
	}


	/**
	 * @methode renomeFile 
	 * @parametre filename.json
	 * @return filename.xml
	 * Renome filename.json en filename.xml
	 **/
	private String renomeFile(String filename) {
		
		/**
		 * @methode getFileName()
		 * @parametre finelame.json
		 * @return filename.xml
		 * */
		return Main.getFileName(filename)+".xml";
		
	}
	
}

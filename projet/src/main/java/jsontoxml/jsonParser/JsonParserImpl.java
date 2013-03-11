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





public class JsonParserImpl implements JsonParser {

	public static void main(String[] args) {

		JsonParserImpl i = new JsonParserImpl();
		//i.parser("src/test/resources/USE_TrueFalse_AUTOGEN.json");
		i.parser(args[0]);
	}



	public void parser(String filename) {
		Reader reader= null;
		JSONObject o = null;
		JSONArray i = null;
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
			// test pour différencier entre une question (type object) de plusieurs question( type array )
			if(o.optJSONArray("question")==null){
				o = o.getJSONObject("question");
				xmlWriter.writeXmlToJson(o, renomFile(filename));
			}
			else{
				i = o.getJSONArray("question");
				xmlWriter.writeXmlToJson(i, renomFile(filename));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}



	private String renomFile(String filename) {
		// TODO modif filename -> X.json -> X.xml


		//on recupere le nom du fichier sans son extension
		String nomCourt = filename.substring(0,filename.indexOf('.'));
		//on rajoute l'extension xml

		String nomxml=nomCourt+".xml";

		return nomxml;


	}
}

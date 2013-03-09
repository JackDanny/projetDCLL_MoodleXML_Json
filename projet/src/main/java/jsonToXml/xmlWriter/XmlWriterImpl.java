package jsonToXml.xmlWriter;

import java.io.FileReader;
import java.io.Reader;
//import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import jsonToXml.jsonParser.JsonParserImpl;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class XmlWriterImpl implements XmlWriter{

	 static Element racine = new Element("quiz");

	   //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	   static org.jdom2.Document document = new Document(racine);

	public void writeXmlToJson(JSONArray tab,  String nameXmlFileOut) {

		for(int i=0; i < tab.length(); ++i ){
			try{
				Class[] paramTypes = new Class[1];
				paramTypes[0]= Object.class;
				JSONObject quesObj = (JSONObject) tab.get(i);	
				String type= quesObj.get("type").toString();
				String className = convertClassName(type);
				Class c = Class.forName("jsonToXml.xmlWriter.questionClass."+className);		
				//Class c = Class.forName("jsonToXml.xmlWriter.questionClass."+"Category");
				java.lang.reflect.Method getXmlContentMethode = c.getMethod("getXmlContent", paramTypes);
				racine.addContent( (Content) getXmlContentMethode.invoke(c.newInstance(), tab.get(i)) ); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		enregistre("xml-out.xml");//save the xml document object 
	}

	
	public static void main(String args[]){
		XmlWriterImpl i = new XmlWriterImpl();
		//JSONArray tab = i.t();		
     	//i.writeXmlToJson(tab, "outXML-file.xml");	
     	i.test();
     	
	}

	private String convertClassName(String type) {
		char [] tab = type.toCharArray();
		tab[0] = type.toUpperCase().toCharArray()[0];
		return new String(tab);
	}

	private void test() {
		JSONArray tab = t();
		//System.out.println(tab.length());
		try {
			Object o = tab.get(0);		
			JSONObject o2 = (JSONObject) o;
			
			Iterator<?> it = o2.keys() ;
			//it.next();
			while(it.hasNext()){
				//System.out.println( o2.get(  it.toString() ));
				System.out.println("> " + it.next());
			//	System.out.println(o2.has("generalfeedback"));
				//it.next();
			}
			
		//	o2.
			
			Object o3 = o2.get("type");
			System.out.println(o3.toString());
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static void enregistre(String fichier)
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
	      //avec en argument le nom du fichier pour effectuer la sérialisation.
	      //sortie.output(document, new FileOutputStream(fichier));
	      sortie.output(document, System.out);

	   }
	   catch (java.io.IOException e){}
	}
	
	
	private JSONArray  t(){
		Reader reader= null;
		JSONObject o2 = null;
		JSONArray t2 = null;
		JSONObject tbis= null;
		
		try {
			reader = new FileReader("src/test/resources/description_question.json");		
			JSONTokener jsonT = new JSONTokener(reader);			
			o2 = new  JSONObject(jsonT);
			tbis = o2.getJSONObject("quiz");
			t2 = tbis.getJSONArray("question");
		}catch(Exception e){
			e.printStackTrace();
		}
		return t2; 
	}
	
	
 

}

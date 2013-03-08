package jsonToXml.xmlWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;

import jsonToXml.xmlWriter.questionClass.Category;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Content.CType;
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

	
	public void writeXmlToJson(JSONArray tab) {
		
		for(int i=0; i < tab.length(); ++i ){
			try{
				Class[] paramTypes = new Class[1];
				paramTypes[0]= Object.class;
				JSONObject quesObj = (JSONObject) tab.get(i);	
				String type= quesObj.get("type").toString();
				String className = convertClassName(type);
				Class c = Class.forName("jsonToXml.xmlWriter.questionClass."+className);		
				//Class c = Class.forName("jsonToXml.xmlWriter.questionClass."+"Category");
				java.lang.reflect.Method m = c.getMethod("getXmlContent", paramTypes);
				racine.addContent( (Content) m.invoke(c.newInstance(), tab.get(i)) ); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		enregistre("xml-out.xml");//save the xml document object 
	}

	
	public static void main(String args[]){
		XmlWriterImpl i = new XmlWriterImpl();
		JSONArray tab = i.t();		
     	i.writeXmlToJson(tab);	
	}
	
	private String convertClassName(String type){
		char [] tab = type.toCharArray();
		tab[0] = type.toUpperCase().toCharArray()[0];
		return new String(tab);
	}
	
	private void test(){
//		Content child = new Content(CType.Text) {
//			@Override
//			public String getValue() {
//				// TODO Auto-generated method stub
//				return "test";
//			}
//		};
//		Content c2 = new Content(CType.Text) {
//			@Override
//			public String getValue() {
//				return "TestElement";
//			}
//		};		
//		Element e = new Element("ELEMTEST");
//		Element e2 = new Element("SUB_ELEMTEST");
//		
//		Attribute att = new Attribute("att_name", "att_val");
//		
//		e.setAttribute(att);
//		
//		e2.addContent(c2);
//		
//		e.addContent(e2);
//		
//		racine = racine.addContent(e);
//		racine.addContent(child);
//		
//		
		JSONArray tab = t();
		
		System.out.println(tab.length());
		
		try {
			Object o = tab.get(0);
		
		
			JSONObject o2 = (JSONObject) o;
		
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
	
	public void arte(){
		
	}
	
	private JSONArray  t(){
		Reader reader= null;
		JSONObject o = null;
		JSONArray t2 = null;
		JSONObject t= null;
	
		try {
			reader = new FileReader("src/test/resources/jsonMoodle.json");		
			JSONTokener jsonT = new JSONTokener(reader);
			o = new  JSONObject(jsonT);
			t = o.getJSONObject("quiz");
			t2 = t.getJSONArray("question");
		}catch(Exception e){
			e.printStackTrace();
		}
		return t2; 
	}
	
}

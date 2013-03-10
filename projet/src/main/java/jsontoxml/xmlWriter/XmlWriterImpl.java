package jsontoxml.xmlWriter;

import java.io.FileOutputStream;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONObject;

public class XmlWriterImpl implements XmlWriter{

	 private Element racine = new Element("quiz");

	   //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
	   private org.jdom2.Document document = new Document(racine);

	public void writeXmlToJson(JSONArray tab,  String nameXmlFileOut) {

		for(int i=0; i < tab.length(); ++i ){
			try{
				JSONObject quesObj = (JSONObject) tab.get(i);	
				reflexiveXMLBuild(quesObj); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		enregistre("xml-out.xml");//save the xml document object 
		//TODO nameXmlFileOut
	}

	private void reflexiveXMLBuild(JSONObject quesObj){
		Class<Object>[] paramTypes = new Class[1];
		paramTypes[0]= Object.class;
		try{
			String type= quesObj.get("type").toString();
			String className = convertClassName(type);
			Class c = Class.forName("jsontoxml.xmlWriter.questionClass."+className);		
			java.lang.reflect.Method getXmlContentMethode = c.getMethod("getXmlContent", paramTypes);
			racine.addContent( (Content) getXmlContentMethode.invoke(c.newInstance(), quesObj ));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private String convertClassName(String type) {
		char [] tab = type.toCharArray();
		tab[0] = type.toUpperCase().toCharArray()[0];
		return new String(tab);
	}

	
	private void enregistre(String fichier)
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
	      //avec en argument le nom du fichier pour effectuer la sérialisation.
	      sortie.output(document, new FileOutputStream(fichier));
	//      sortie.output(document, System.out);

	   }
	   catch (java.io.IOException e){}
	}
	


	public void writeXmlToJson(JSONObject oneQuestion, String nameXmlFileOut) {
		reflexiveXMLBuild(oneQuestion);
		enregistre(nameXmlFileOut);//save the xml document object 

	}
	
	
 

}


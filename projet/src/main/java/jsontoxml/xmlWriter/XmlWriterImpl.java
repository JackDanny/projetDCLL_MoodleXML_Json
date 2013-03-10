package jsontoxml.xmlWriter;

import java.io.FileOutputStream;

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
				buildXML(quesObj); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		enregistre("xml-out.xml");//save the xml document object 
		//TODO nameXmlFileOut
	}


	private String convertClassName(String type) {
		char [] tab = type.toCharArray();
		tab[0] = type.toUpperCase().toCharArray()[0];
		return new String(tab);
	}

	private void buildXML(JSONObject quesObj){
	    GenXML generator = new GenXML();
	    generator.addElments(quesObj);
	    racine.addContent( generator.getCommonTags());
	}
	
	private void enregistre(String fichier)
	{
	   try
	   {
	      //On utilise ici un affichage classique avec getPrettyFormat()
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document, new FileOutputStream(fichier));
	//      sortie.output(document, System.out);

	   }
	   catch (java.io.IOException e){}
	}
	
	public void writeXmlToJson(JSONObject oneQuestion, String nameXmlFileOut) {
		buildXML(oneQuestion);
		enregistre(nameXmlFileOut);//save the xml document object 

	}
 

}


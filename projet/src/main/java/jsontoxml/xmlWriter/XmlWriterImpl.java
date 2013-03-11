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

	 private org.jdom2.Document document = new Document(racine);

	 
	 public void writeXmlToJson(JSONObject oneQuestion, String nameXmlFileOut) {
	        buildXML(oneQuestion);
	        enregistre(nameXmlFileOut);//save the xml document object 
	  }
	 	   
	 
	public void writeXmlToJson(JSONArray tab,  String nameXmlFileOut) {
		for(int i=0; i < tab.length(); ++i ){
			try{
				JSONObject quesObj = (JSONObject) tab.get(i);	
				buildXML(quesObj); 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		enregistre(nameXmlFileOut);//save the xml document object 
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
	      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	      sortie.output(document, new FileOutputStream(fichier));
	//      sortie.output(document, System.out);

	   }
	   catch (java.io.IOException e){}
	}
	

}


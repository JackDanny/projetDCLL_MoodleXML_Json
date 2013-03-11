package main;

import java.io.File;


import jsontoxml.jsonParser.JsonParserImpl;



import xmltojson.jsonWriter.ToJson;
import xmltojson.xmlparser.XmlParserImpl;


public class Main {

	public static String getFileExtension(String NomFichier) {
	    File tmpFichier = new File(NomFichier);
	    tmpFichier.getName();
	    int posPoint = tmpFichier.getName().lastIndexOf('.');
	    if (0 < posPoint && posPoint <= tmpFichier.getName().length() - 2 ) {
	        return tmpFichier.getName().substring(posPoint + 1);
	    }    
	    return "";
	}
	public static String getFileName(String NomFichier) {
	  
	    int posPoint = NomFichier.lastIndexOf('.');
	    if (0 < posPoint && posPoint <= NomFichier.length() - 2 ) {
	        return NomFichier.substring(0,posPoint);
	    }    
	    return "";
	}
	
	public static void main(String[] args) {

		String ext=getFileExtension(args[0]);
		
		try{
			if (ext.toUpperCase().equals("XML") ){
				
				    XmlParserImpl xmlparser1 = new XmlParserImpl();
					ToJson tj = new ToJson(getFileName(args[0])+".json");
					tj.toJson(xmlparser1.parser(args[0]));
				       }
			
				else if (ext.toUpperCase().equals("JSON")){
					
				    JsonParserImpl i = new JsonParserImpl();
				    i.parser(args[0]);		
				}
				else {
					
					System.out.println("fichier non pris en charge");
				}
		     }
		catch(Exception e){
				System.out.println("fichier inexistant");
				 }		
	}

}

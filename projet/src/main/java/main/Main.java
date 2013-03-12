package main;

import java.io.File;


import jsontoxml.jsonParser.JsonParserImpl;



import xmltojson.jsonWriter.ToJson;
import xmltojson.xmlparser.XmlParserImpl;


public class Main {
	/**
     * @param nomFichier the file name
     * @return a string.
     * Return the extension of a file from its name.
     * */
	public static String getFileExtension(final String nomFichier) {
	    File tmpFichier = new File(nomFichier);
	    tmpFichier.getName();
	    int posPoint = tmpFichier.getName().lastIndexOf('.');
	    if (0 < posPoint &&
	    		posPoint <= tmpFichier.getName().length() - 2) {
	        return tmpFichier.getName().substring(posPoint + 1);
	        }    
	    return "";
	}
	/**
     * @param nomFichier the file name
     * @return a string.
     * Return the name without an extension of a file from its complete name .
     * */
	public static String getFileName(final String  nomFichier){  
	    int posPoint = nomFichier.lastIndexOf('.');
	    if (0 < posPoint && posPoint <= nomFichier.length() - 2){
	        return nomFichier.substring(0, posPoint);
	    }    
	    return "";
	}
	/**
     * @param args the file name
     * Generates an xml file if the file parameter is a json file .
     * Generates a json file if the file parameter is an xml file .
     * */	
	public static void main(final String[] args) {
		String ext = getFileExtension(args[0]);	
		try {
			if (ext.toUpperCase().equals("XML")){
				XmlParserImpl xmlparser1 = new XmlParserImpl();
				ToJson tj = new ToJson(
					 getFileName(args[0]) + ".json");
				tj.toJson(xmlparser1.parser(args[0]));
				       }
			else if (ext.toUpperCase().equals("JSON")) {
				JsonParserImpl i = new JsonParserImpl();
				i.parser(args[0]); 
				}					       
			else {
				System.out.println(
						"fichier non pris en charge");
				}
		     }
		catch (Exception e){
				System.out.println("fichier inexistant");
				}		
	}

}

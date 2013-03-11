package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import xmltojson.xmlparser.XmlParserImpl;




import xmltojson.jsonWriter.ToJson;


import org.jdom2.Element;

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
		System.out.println(ext);
		System.out.println(args[0]);
		System.out.println(getFileName(args[0]));

	if (ext.equals("xml") || ext.equals("XML")){
		        List<Element> elems = new ArrayList<Element>();
		        XmlParserImpl xmlparser1 = new XmlParserImpl();
		        elems = xmlparser1.parser(args[0]);
		        ToJson tj = new ToJson(getFileName(args[0])+"2.json");
		        tj.toJson(elems);
		}
		else if (ext.equals("json") || ext.equals("JSON")){
				
		}
		else {
		System.out.println("fichier non pris en charge");
		}

	}

}

package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;



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
	public static void main(String[] args) {
		
		 final String path = "src/test/resources/";
		
		// XML TO JSON
		  
	    
	    JFrame frame = new JFrame("projetDCLL_MoodleXML_Json");	 
	    frame.setSize(500, 500);
	    frame.setBounds(500, 500, 400, 200);	    
	    JPanel panel = new JPanel();	   	    
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    JButton xmlToJson = new JButton("xmlToJson");	  
	    xmlToJson.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          System.out.println(selectedFile.getName());
	          XmlParserImpl xmlparser1 = new XmlParserImpl();			 
			ToJson tj = new ToJson(getFileName(path+selectedFile.getName()));
			  tj.toJson(xmlparser1.parser(path+selectedFile.getName()));
	        }
	      }
	    });
	    
	    panel.add(xmlToJson);
	    frame.add(panel);
	    
	    
	    // JSON TO XML
	    
	    JButton jsonToXml = new JButton("jsonToXml"); 
	    jsonToXml.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          System.out.println(path+selectedFile.getName());
	          JsonParserImpl i = new JsonParserImpl();
			    i.parser(path+selectedFile.getName());
	        }
	      }
	    });
	    
	    panel.add(jsonToXml);
	    frame.add(panel);	    
	    frame.setVisible(true);

	}

}

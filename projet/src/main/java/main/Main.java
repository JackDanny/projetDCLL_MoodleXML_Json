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

         final String path = "src/test/resources/";
        /*
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
                 }      */
        
        
        
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
            ToJson tj = new ToJson(getFileName(selectedFile.getPath())+".json");
              tj.toJson(xmlparser1.parser(selectedFile.getPath()));
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
                i.parser(selectedFile.getPath());
            }
          }
        });
        
        panel.add(jsonToXml);
        frame.add(panel);       
        frame.setVisible(true);
    }

}

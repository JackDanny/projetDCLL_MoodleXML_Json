package xmlParser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import org.w3c.dom.*;




public class XmlParser {

	
	public Document parser(String fileName ){
		// création d'une fabrique de constructeur de documents DOM
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		// création d'un constructeur de documents DOM
		DocumentBuilder constructeur;
		Document document = null;
		try {
			constructeur = fabrique.newDocumentBuilder();
			// lecture du contenu d'un fichier XML avec le constructeur pour
			// créer le document DOM correspondant
			File xmlFile = new File(fileName);
			document = constructeur.parse(xmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		ExploreDocument(document);
		return document;
	}
	
	
	public static void exploreNode(Node node) {
		System.out.println(node);
		//node.
//		System.out.println("la "+  node.getLocalName());
		if(node.getAttributes() != null){
	//		System.out.println(node.getAttributes().));
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeList nodes = node.getChildNodes();
		for(int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			exploreNode(n);
		}
	}
	
	public static void ExploreDocument(Document document) {
		Element racine = document.getDocumentElement();
		exploreNode(racine);
	}
	

	
	
}

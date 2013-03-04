package xmlParser;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import nomenclature.QuestionType_enum;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;




public class XmlParser {

	   private org.jdom2.Document document;
	   private Element racine;

	
	public final void parser(final String fileName ){
	      SAXBuilder sxb = new SAXBuilder();
	      document = null;
	      try
	      {
	         //On crée un nouveau document JDOM avec en argument le fichier XML
	         //Le parsing est terminé ;)
	         document = sxb.build(new File(fileName));
	      }
	      catch(Exception e){
	    	  //TODO
	    	  e.printStackTrace();
	      }
	      
	      //On initialise un nouvel élément racine avec l'élément racine du document.
	      racine = document.getRootElement();

	      afficheALL(racine);
	   }

	
	private void afficheALL(final Element racine)
	   {
		   QuestionType_enum qType_enum;
		   List<Element> listQuestion = racine.getChildren("question");
		   System.out.println(listQuestion.size());

		   Iterator<Element> i = listQuestion.iterator();
	      while(i.hasNext())
	      {
	         Element courant = (Element)i.next();
	        
	         qType_enum = QuestionType_enum.valueOf(courant.getAttributeValue("type"));
	         if(qType_enum.isImplemented()){
	        	 System.out.println(qType_enum.name() + " traité");
	        	 List<Element> le= courant.getChildren();
	         }else{
	        	 System.out.println("Les questions de type " + qType_enum.name() + " ne peuvent être traités.");
	         }
	         
//	         System.out.println(courant.getAttributeValue("type"));
	         
	      }
	   }


	
	
}

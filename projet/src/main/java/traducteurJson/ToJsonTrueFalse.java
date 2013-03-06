package traducteurJson;

import xmlparser.XmlParserImpl;

import java.util.ArrayList;
import java.util.List;


import org.jdom2.Attribute;
import org.jdom2.Element;


public class ToJsonTrueFalse {
    
    
    //TODO rajouter le fichier dans lequel on doit écrire la traduction
    
    public static void toJson(Element questionType){
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        
        System.out.println("question : "+questionType.getName());
        
        //recupere les attributs de la question
        attList = questionType.getAttributes();
        System.out.println("                nb attributs : "+attList.size());
        //TODO traduit cette partie en JSON = question + attributs
        
        System.out.println("************Changement de niveau+********");
        
        //On recupere les fils
        childrens = questionType.getChildren();
        if(!childrens.isEmpty()){
            //traiter chaque fils en profondeur
            for(Element children : childrens){
                System.out.println("children : "+children.getName());
                
                //recupere les attributs de l element
                attList = children.getAttributes();
                System.out.println("                nb attributs : "+attList.size());
                //TODO Besoin de traiter le etxte à ce niveau ?????
                //TODO traduit cette partie en JSON = element + attributs
                
                //System.out.println("************Changement de niveau+********");
                toJsonFils(children);
                //System.out.println("************Changement de niveau-********");
            }
        }
        
        
    }
    
    
    
    
    
    
    private static void toJsonFils(Element children) {
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        String text;
        
        
        //On recupere les fils
        childrens = children.getChildren();
        if(!childrens.isEmpty()){
            System.out.println("************Changement de niveau+********");
            //traiter chaque fils en profondeur
            for(Element fils : childrens){
                
                System.out.println("children : "+fils.getName());
                
              //recupere les attributs de l element
                attList = fils.getAttributes();
                System.out.println("                nb attributs : "+attList.size());
                text = children.getChildText(fils.getName());
                System.out.println("                texte contenu : "+text);
                //TODO traduit cette partie en JSON = element + attributs + texte eventuel
                
                
                toJsonFils(fils);
            }
            System.out.println("************Changement de niveau-********");
        }
        
    }






    public static void main(String[] args) {
        List<Element> elems = new ArrayList<Element>();
        
        System.out.println("deb test ToJsonTF");
        XmlParserImpl xmlparser = new XmlParserImpl();
        elems = xmlparser.parser("src/test/resources/TrueFalse.xml");
        //System.out.println(elems.size());
        toJson(elems.get(0));
        System.out.println("fin test ToJsonTF");
    }

}

package traducteurJson;

import xmlparser.XmlParserImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jdom2.Attribute;
import org.jdom2.Element;


public class ToJsonTrueFalse {
    
    private FileWriter fw;
    private BufferedWriter output;
    private int cptLevel = 0;
    private boolean newLevel = true;
    
    
    //TODO rajouter le fichier dans lequel on doit écrire la traduction dans un constructeur
    
    public void toJson(Element questionType){
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        
        try {
            fw = new FileWriter("src/test/resources/TrueFalse.json");
            output = new BufferedWriter(fw);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //System.out.println("question : "+questionType.getName());
        
        //recupere les attributs de la question
        attList = questionType.getAttributes();
        //System.out.println("                nb attributs : "+attList.size());
        //TODO traduit cette partie en JSON = question + attributs
        try {
            ajoutTab();
            output.write("{\"question\" : \r\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //System.out.println("************Changement de niveau+********");
        levelUp();
        for(Attribute att : attList){
            try {
                ajoutTab();
                output.write("\""+att.getName()+"\""+" : "+"\""+att.getValue()+"\""+"\r\n");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
//        System.out.println("************Changement de niveau+********");
//        cptLevel++;
        
        //On recupere les fils
        childrens = questionType.getChildren();
        if(!childrens.isEmpty()){
            //traiter chaque fils en profondeur
            for(Element children : childrens){
                //System.out.println("children : "+children.getName());
                
                //recupere les attributs de l element
                attList = children.getAttributes();
                //System.out.println("                nb attributs : "+attList.size());
                //TODO Besoin de traiter le etxte à ce niveau ?????
                //TODO traduit cette partie en JSON = element + attributs
                ajoutTab();
                try {
                    output.write("\""+children.getName()+"\""+" : \r\n");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                levelUp();
                for(Attribute att : attList){
                    try {
                        ajoutTab();
                        output.write("\""+att.getName()+"\""+" : "+"\""+att.getValue()+"\""+"\r\n");
                        newLevel = false;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
                //System.out.println("************Changement de niveau+********");
                toJsonFils(children);
                //System.out.println("************Changement de niveau-********");
            }
        }
        
        try {
            output.write("} }");
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    


    public void toJsonFils(Element children) {
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        String text;
        
        
        //On recupere les fils
        childrens = children.getChildren();
        if(!childrens.isEmpty()){
            //System.out.println("************Changement de niveau+********");
            levelUp();
            //traiter chaque fils en profondeur
            for(Element fils : childrens){
                
                //System.out.println("children : "+fils.getName());
                
              //recupere les attributs de l element
                attList = fils.getAttributes();
                //System.out.println("                nb attributs : "+attList.size());
                text = children.getChildText(fils.getName());
                //System.out.println("                texte contenu : "+text);
                //TODO traduit cette partie en JSON = element + attributs + texte eventuel
                if(!attList.isEmpty()) {
                    ajoutTab();
                    try {
                        output.write("\""+fils.getName()+"\""+" : \r\n");
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    levelUp();
                    for(Attribute att : attList){
                        try {
                            ajoutTab();
                            output.write("\""+att.getName()+"\""+" : "+"\""+att.getValue()+"\""+"\r\n");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else{//TODOSeulement attribut ou texte pas les deux ????
                    try {
                        ajoutTab();
                        output.write("\""+fils.getName()+"\""+" : ");
                        if(!text.isEmpty()){
                            output.write("\""+text+"\""+"\r\n");
                        }
                        else
                            output.write("\r\n");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
                
                toJsonFils(fils);
            }
            //System.out.println("************Changement de niveau-********");
            levelDown();
        }
        
    }
    
    public void ajoutTab(){
        for(int i=0;i<cptLevel;i++){
            try {
                output.write("\t");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void levelUp() {
        if(newLevel){
            ajoutTab();
            try {
                output.write("{\r\n");
            } catch (IOException e) {
                //  TODO Auto-generated catch block
                e.printStackTrace();
            }
            cptLevel++;
        }
        newLevel = true;
    }
    
    public void levelDown(){
        cptLevel--;
        ajoutTab();
        try {
            output.write("}\r\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

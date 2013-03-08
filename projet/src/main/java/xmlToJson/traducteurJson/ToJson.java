package xmlToJson.traducteurJson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jdom2.Attribute;
import org.jdom2.Element;

import xmlToJson.xmlparser.XmlParserImpl;

public class ToJson {

    public ToJson(String pathFile) {
        super();
        this.pathFile = pathFile;
    }

    private String pathFile;
    private JsonGen jg = new JsonGen();
    private Map balise = new LinkedHashMap();
    
    @SuppressWarnings("unchecked")
    public void toJson(List<Element> questions){
        JSONObject oJson = new JSONObject();
        
        
        //construire l'arborescence map pour chaque question
        for(Element elem : questions){
            balise.put("question",toJson(elem));
        }
        
        //Remplir le JSONObject
        oJson.put("quiz", balise);
        
        
        //ecrire dans le fichier
        SaveFileJson save = new SaveFileJson(oJson.toString(),pathFile);
        save.sauvegarde();
        
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map toJson(Element element){
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        Map courante = new LinkedHashMap();
        
        //recupere les attributs de la question
        attList = element.getAttributes();
        
        if(!attList.isEmpty()){
            for(Attribute att : attList){
                courante.put(att.getName(), att.getValue());
            }
        }
        
        //TODO gérer le contenu textuel
        //soucis getText recup aussi les retour ligne \n et tab \t
//        String text = element.getText();
//        if(text!=null){
//            System.out.println(text);
//            courante.put("", text);
//        }
        
        //On recupere les fils
        childrens = element.getChildren();
        if(!childrens.isEmpty()){
            //traiter chaque fils en profondeur 
            //CARE : Selon ce code une balise ne peut avoir texte ET (attributs ou sous balises).
            //TODO gere les [] si nextchildrens identiques, sinon la hash map vire le précédent
            for(Element children : childrens){
//                courante.put(children.getName(), toJson(children));
                if(addChild(children) || addAttributes(children)) {
                    courante.put(children.getName(), toJson(children));
                }
                else {
                    String text = children.getValue();
                    if(text!=null){
                        courante.put(children.getName(), text);
                    }
                }
                    
            }
        }
        return courante;
        
    }
    
    public boolean addChild(Element e){
        return !(e.getChildren().isEmpty());
    }
    
    public boolean addAttributes(Element e){
        return !(e.getAttributes().isEmpty());
    }
    
    public static void main(String[] args) {    
        List<Element> elems = new ArrayList<Element>();
        
        System.out.println("deb test ToJson");
        XmlParserImpl xmlparser = new XmlParserImpl();
        elems = xmlparser.parser("src/test/resources/TrueFalse.xml");
        
        ToJson tj = new ToJson("src/test/resources/TrueFalse.json");
        tj.toJson(elems);
        
    }
    
    

}


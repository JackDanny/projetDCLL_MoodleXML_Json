/**
 * Package de gestion traduction/ecriture enJson.
 * <p>
 * Utilise principalement la classe { @link org.jdom2.Element }
 */
package xmlToJson.jsonWriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jdom2.Attribute;
import org.jdom2.Element;

import xmlToJson.xmlparser.XmlParserImpl;

/**
 * Classe pour pouvoir ecrire un fichier Json.
 * @author Raphaël
 *
 */
public class ToJson {

    /**
     * Constructor ToJson
     * @param pathFile Chemin du fichier en sortie.
     */
    public ToJson(String pathFile) {
        super();
        this.pathFile = pathFile;
    }

    private String pathFile;
    private JsonGen jg = new JsonGen();
    private Map balise = new LinkedHashMap();
    
    /**
     * Traduire en objet Json et ecriture dans le fichier.
     * @param questions Liste des questions du quiz MoodleXML
     */
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
    /**
     * Traduire en objet Json et ecriture dans le fichier.
     * @param element traite une element Jdom2
     * @return Map contenant l'arborescence du parametre element traite
     */
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
        
        //On recupere les fils
        childrens = element.getChildren();
        if(!childrens.isEmpty()){
            //traiter chaque fils en profondeur 
            
            
            //***********************************
            //Gestion des listes, si fils suivant(s) identique(s)
            //CARE si les fils identiques ne sont pas les premiers fils problème.
            int cpt = cptEquals(childrens);
            if(cpt != 0){
                cpt++;//maj pour compter le fils courant
                List<Map> l = creerListes(childrens, cpt);
                courante.put(childrens.get(0).getName(), l);
            }
            
            
            
            //CARE : Selon ce code une balise ne peut avoir texte ET (attributs ou sous balises).
            //TODO gere les [] si nextchildrens identiques, sinon la hash map vire le précédent
            for(Element children : childrens){

                //******************************************le if else
                if(cpt == 0) {//non traité
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
                else cpt--;
                    
            }
        }
        return courante;
        
    }
    
    /**
     * Cree les listes dans le cas de traduction en Json Array
     * @param childrens Liste des fils de l'element courant
     * @param cpt compteur nombre de fils identiques
     * @return Liste de Map des element jdom2 fils identiques
     */
    private List<Map> creerListes(List<Element> childrens, int cpt) {
        List<Map> list = new ArrayList<Map>();
        for(int i=0; i<cpt;i++){
            list.add(toJson(childrens.get(i)));
        }
        
        return list;
        
    }

    /**
     * Compte le nombre de fils identiques
     * @param childrens Liste des fils de l'element courant
     * @return nombre de fils identiques au premier de la liste childrens
     */
    private int cptEquals(List<Element> childrens) {
        int cpt = 0;
        Element children = childrens.get(0);
        for(int i=1;i<childrens.size();i++)
            if(children.getName().equals(childrens.get(i).getName()))
                cpt++;
        return cpt;
    }

    /**
     * teste si l element courant possede un fils
     * @param e element courant
     * @return boolean a un fils?
     */
    public boolean addChild(Element e){
        return !(e.getChildren().isEmpty());
    }
    
    /**
     * Teste si l element courant a un attribut
     * @param e element courant
     * @return boolean a un attribut?
     */
    public boolean addAttributes(Element e){
        return !(e.getAttributes().isEmpty());
    }
    
    /**
     * Main pour test
     * @param args
     */
    public static void main(String[] args) {    
        List<Element> elems = new ArrayList<Element>();
        
        System.out.println("deb test ToJson");
        XmlParserImpl xmlparser = new XmlParserImpl();
        elems = xmlparser.parser("src/test/resources/TrueFalse.xml");
        
        ToJson tj = new ToJson("src/test/resources/TrueFalse.json");
        tj.toJson(elems);
        
    }
    
    

}


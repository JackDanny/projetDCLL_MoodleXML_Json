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
 * Classe pour pouvoir traduire un fichier Json.
 * @author Raphaël
 *
 */
public class ToJson {

    /**
     * Constructor ToJson.
     * @param pathFile Chemin du fichier en sortie.
     */
    public ToJson(final String pathFile) {
        super();
        this.pathFile = pathFile;
    }

    /**
     * Chemin fichier en sortie.
     */
    private String pathFile;
    /**
     * Map d'arborescende du doc Json complété au long du traitement.
     */
    private Map balise = new LinkedHashMap();
    
    /**
     * Traduire en objet Json et ecriture dans le fichier.
     * @param questions Liste des questions du quiz MoodleXML
     */
    @SuppressWarnings("unchecked")
    public void toJson(List<Element> questions) {
        JSONObject oJson = new JSONObject();
        
        
        //construire l'arborescence map pour chaque question
        for (Element elem : questions) {
            balise.put("question", toJson(elem));
        }
        
        //Remplir le JSONObject
        oJson.put("quiz", balise);
        
        
        //ecrire dans le fichier
        SaveFileJson save = new SaveFileJson(oJson.toString(), pathFile);
        save.sauvegarde();
        //TODO Re-indempter le fichier.
        
    }
    /**
     * Traduire en objet Json et ecriture dans le fichier.
     * @param element traite une element Jdom2
     * @return Map contenant l'arborescence du parametre element traite
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map toJson(Element element) {
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        Map courante = new LinkedHashMap();
        
        //recupere les attributs de la question
        attList = element.getAttributes();
        
        if (!attList.isEmpty()) {
            for(Attribute att : attList){
                courante.put(att.getName(), att.getValue());
            }
        }
        
        //On recupere les fils
        childrens = element.getChildren();
        if (!childrens.isEmpty()) {
            //traiter chaque fils en profondeur.
            //CARE:Selon code une balise ne peut avoir texte ET(attribut ou sous balise).
            // i : compteur elem courant
            int i = 0;
            int cpt = 0;
            for (Element children : childrens) {

                if (cpt == 0) { //non traité si deja fait
                    //CARE les fils identiques a gerer en Array doivent se suivre.
                    cpt = cptEquals(childrens, children, i);
                    if (cpt != 0) {
                      cpt++; //maj pour compter le fils courant
                      List<Map> l = creerListes(childrens, cpt, i);
                      courante.put(childrens.get(0).getName(), l);
                    }
                    else {
                        if (addChild(children) || addAttributes(children)) {
                            courante.put(children.getName(), toJson(children));
                        } else {
                            String text = children.getValue();
                            if (text != null) {
                                courante.put(children.getName(), text);
                            }
                        }
                    }
                }
                else cpt--;
                i++;
            }
        }
        return courante;
    }
    
    /**
     * Cree les listes dans le cas de traduction en Json Array.
     * @param childrens Liste des fils de l'element courant
     * @param cpt Nombre de fils identiques
     * @param j position premier fils dans liste
     * @return Liste de Map des element jdom2 fils identiques
     */
    public List<Map> creerListes(List<Element> childrens, int cpt, int j) {
        List<Map> list = new ArrayList<Map>();
        for (int i = j; i < cpt + j; i++) {
            list.add(toJson(childrens.get(i)));
        }
        return list;
    }


    /**
     * Compare suivants à partir de courant et renvoie le nombre de balises identiques.
     * @param childrens Liste des fils de l'element
     * @param courant fils etudie
     * @param i position de ce fils
     * @return nombre de fils identiques a courant dans la suite de la liste childrens
     */
    public int cptEquals(List<Element> childrens, Element courant, int i) {
        int cpt = 0;
        for (int j = i + 1; j < childrens.size(); j++)
            if (courant.getName().equals(childrens.get(j).getName()))
                cpt++;
        return cpt;
    }

    /**
     * teste si l element courant possede un fils.
     * @param e element courant
     * @return boolean a un fils?
     */
    public boolean addChild(Element e) {
        return !(e.getChildren().isEmpty());
    }
    
    /**
     * Teste si l element courant a un attribut.
     * @param e element courant
     * @return boolean a un attribut?
     */
    public boolean addAttributes(Element e) {
        return !(e.getAttributes().isEmpty());
    }
    
    /**
     * Main pour test.
     * @param args arguments du main
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

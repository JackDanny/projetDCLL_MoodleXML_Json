/**
 * Package de gestion traduction/ecriture enJson.
 * <p>
 * Utilise principalement la classe { @link org.jdom2.Element }
 */
package xmltojson.jsonWriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.jdom2.Attribute;
import org.jdom2.Element;

import xmltojson.xmlparser.XmlParserImpl;


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
    @SuppressWarnings({ "rawtypes" })
    private Map balise = new LinkedHashMap();

    /**
     * Realise les appels aux parseurs, méthodes de traduction, ecriture.
     * @param inPathFile Chemin fichier entrée
     */
    public final void toJson(final String inPathFile) {
        List<Element> elems = new ArrayList<Element>();
        XmlParserImpl xmlparser = new XmlParserImpl();
        elems = xmlparser.parser(inPathFile);
        toJson(elems);
    }

    /**
     * Traduire en objet Json et ecriture dans le fichier.
     * @param questions Liste des questions du quiz MoodleXML
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final void toJson(final List<Element> questions) {
        JSONObject oJson = new JSONObject();


        //construire l'arborescence Map/List
        if (questions.size() == 1) { //pour une question
            balise.put(questions.get(0).getName(), toJson(questions.get(0)));
        } else { //pour plusieurs questions
            List<Map> l = creerListes(questions, questions.size(), 0);
            balise.put(questions.get(0).getName(), l);
        }

        //Remplir le JSONObject
        oJson.put("quiz", balise);


        //ecrire dans le fichier
        SaveFileJson save = new SaveFileJson(oJson.toString(1), pathFile);
        save.sauvegarde();
        //TODO Re-indempter le fichier.
        //save.indempter();

    }

    /**
     * Traduire en objet Json et ecriture dans le fichier.
     * @param element traite une element Jdom2
     * @return Map contenant l'arborescence du parametre element traite
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final Map toJson(final Element element) {
        List<Attribute> attList = new ArrayList<Attribute>();
        List<Element> childrens = new ArrayList<Element>();
        List<String> isDone = new ArrayList<String>();
        Map courante = new LinkedHashMap();
        
//        if (element.getName().equals("question")){
//            System.out.println("debug");
//        }

        //recupere les attributs de la question
        attList = element.getAttributes();

        if (!attList.isEmpty()) {
            for (Attribute att : attList) {
                courante.put(att.getName(), att.getValue());
            }
        }

        //On recupere les fils
        childrens = element.getChildren();
        if (!childrens.isEmpty()) {
            //traiter chaque fils en profondeur.
            //CARE:Selon code une balise ne peut avoir :
            //    texte ET(attribut ou sous balise).
            // i : compteur elem courant
            int i = 0;
            for (Element children : childrens) {

              //non traité si deja fait
                if (!isDone.contains(children.getName())) {
                  //plus d'un fils de même nom
                    if (getEqualsChildrens(childrens, children, i)) {
                      List<Map> l = creerListes(element, children);
                      courante.put(childrens.get(i).getName(), l);
                      isDone.add(children.getName());
                    }
                    else {
                        if (addChild(children) || addAttributes(children)) {
                            courante.put(children.getName(), toJson(children));
                        } else {
                            String text = children.getValue();
                            text = text.replaceAll("[\n]+", "");
                            if (!text.equals("")) {
                                courante.put(children.getName(), text);
                            }  else { courante.put(children.getName(), null); }
                        }
                    }
                }
                i++;
            }
        } else {
            String text = element.getValue();
            text = text.replaceAll("[\n]+", "");
            if (!text.equals("")) {
                courante.put(element.getName(), text);
            }  else { courante.put(element.getName(), null); }
        }
        return courante;
    }

    /**
     * Traite le contenu d'un element.
     * @param element elem courant
     * @return Map du contenu de l'element
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final Map toJsonContenu(final Element element) {
        List<Element> childrens = new ArrayList<Element>();
        List<Attribute> attList = new ArrayList<Attribute>();
        Map courante = new LinkedHashMap();

        //recupere les attributs de la question
        attList = element.getAttributes();

        if (!attList.isEmpty()) {
            for (Attribute att : attList) {
                courante.put(att.getName(), att.getValue());
            }
        }

        //On recupere les fils
        childrens = element.getChildren();
        if (!childrens.isEmpty()) {
            //traiter chaque fils en profondeur.
            for (Element children : childrens) {
                //appel au toJson normal
                //courante.put(children.getName(), toJson(children));

                if (addChild(children) || addAttributes(children)) {
                    courante.put(children.getName(), toJson(children));
                } else {
                    String text = children.getValue();
                    text = text.replaceAll("[\n]+", "");
                    if (!text.equals("")) {
                        courante.put(children.getName(), text);
                    }  else { courante.put(children.getName(), null); }
                }
            }
        } else {
            String text = element.getValue();
            text = text.replaceAll("[\n]+", "");
            if (!text.equals("")) {
                courante.put(text, null);
            }  //else { courante.put(element.getName(), null); }
        }
        return courante;
    }

    /**
     * Cree les listes dans un cas particulier de traduction en Json Array.
     * @param childrens Liste des fils de l'element courant
     * @param cpt Nombre de fils identiques
     * @param j position premier fils dans liste
     * @return Liste de Map des element jdom2 fils identiques
     */
    @SuppressWarnings("rawtypes")
    public final List<Map> creerListes(final List<Element> childrens,
            final int cpt, final int j) {
        List<Map> list = new ArrayList<Map>();
        for (int i = j; i < cpt + j; i++) {
            list.add(toJson(childrens.get(i)));
        }
        return list;
    }

    /**
     * Creer une liste dans le cas général de trad JsonArray.
     * @param father element pere
     * @param children element courant
     * @return La liste de map du courant
     */
    @SuppressWarnings("rawtypes")
    public final List<Map> creerListes(final Element father,
            final Element children) {
        List<Map> list = new ArrayList<Map>();
        List<Element> sameChildrens = new ArrayList<Element>();
        sameChildrens = father.getChildren(children.getName());
        for (Element child : sameChildrens) {
            list.add(toJsonContenu(child));
        }
        return list;
    }


    /**
     * Teste si la liste possede plusieurs elements courant.
     * @param childrens liste des fils
     * @param courant element à tester
     * @param i position de courant dans la liste
     * @return boolean possede plusieurs elements courants.
     */
    public final boolean getEqualsChildrens(final List<Element> childrens,
            final Element courant, final int i) {
        int cpt = 0;
        for (int j = i + 1; j < childrens.size(); j++)
            if (courant.getName().equals(childrens.get(j).getName()))
                cpt++;
        return cpt > 0;
    }

    /**
     * Compare suivants à partir de courant
     * et renvoie le nombre de balises identiques.
     * @param childrens Liste des fils de l'element
     * @param courant fils etudie
     * @param i position de ce fils
     * @return nombre de fils identiques a courant
     * dans la suite de la liste childrens
     */
    public final int cptEquals(final List<Element> childrens,
            final Element courant, final int i) {
        int cpt = 0;
        for (int j = i + 1; j < childrens.size(); j++)
            if (courant.getName().equals(childrens.get(j).getName()))
                cpt++;
        return cpt;
    }

    /**
     * Teste si l element courant possede un fils.
     * @param e element courant
     * @return boolean a un fils?
     */
    public final boolean addChild(final Element e) {
        return !(e.getChildren().isEmpty());
    }

    /**
     * Teste si l element courant a un attribut.
     * @param e element courant
     * @return boolean a un attribut?
     */
    public final boolean addAttributes(final Element e) {
        return !(e.getAttributes().isEmpty());
    }
}

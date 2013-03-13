package jsontoxml.xmlWriter;

import java.util.Iterator;

import nomenclature.TagsKnown;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Génère le code XML associé à un objet JSON Moodle question.
 * @author florent
 * */
public class GenXML {

    /**Element question de l'arbre JDom.*/
    private final transient Element quest;

    /**Constructeur.*/
    public GenXML() {
        quest = new Element("question");
    }

    /**Retourne un élement JDom contenant les balises
     * XML d'une Moodle question.
     * @return Le Jdom question élement
     * */
    final Element getQuestionElem() {
        return quest;
    }

    /**
     * Ajoute un élément à la racine : l'élément question moodle.
     * @param content : l'élément à ajouter
     * */
    private void addElementToRoot(final Content content) {
        quest.addContent(content);
    }

    /**
     * Créé un élément simple a partir de jsonO et de son nom.
     * @param jsonO l'élément json contenant l'élément simple
     * @param name le nom de l'élément à ajouté.
     * @return l'élément simple
     * @throws JSONException peut générer une exception json.
     * */
    private  Element createSimpleTags(final JSONObject jsonO, final String name)
            throws JSONException {
        Element elemRet = null;
        elemRet = new Element(name);
        final String text;
        if (jsonO.isNull(name)) {
            text = "";
        } else {
            text = jsonO.getString(name);
        }
        elemRet.setText(text);
        return elemRet;
    }

    /**
     * Ajoute l'élément est les sous-éléments "questiontext".
     * @param jsonO l'élément json "questiontext"
     * @throws JSONException les exception JSON
     * */
    private void addQuestiontext(final JSONObject jsonO) throws JSONException {
        final Element questionText = new Element("questiontext");
        final Element textquestionText  = createSimpleTags(jsonO, "text");
        addFormatAttribute(jsonO, questionText);
        questionText.addContent(textquestionText);
        addElementToRoot(questionText);
    }

    /**
     * Ajout, s'il existe dans le json l'attribut, "format"
     * dans l'élément questionText.
     * @param jsonO : l'élément json qui peut contenir l'attribut
     * @param questionText l'élément où ajouté l'attribut
     * @throws JSONException exception JSON
     * */
    private void addFormatAttribute(final JSONObject jsonO
            , final Element questionText) throws JSONException {
        if (jsonO.has("format")) {
            final String fomatValue = jsonO.getString("format");
            Attribute att = new Attribute("format", fomatValue);
            questionText.setAttribute(att);
        }
    }


    /**
     * Détermine s'il faut ajouté un tableau
     * answer ou juste un objet.
     * @param jsonO : l'objet Json contenant
     * la clé "answer"
     * @throws JSONException exception JSON
     */
    private void preAddAnswer(final JSONObject jsonO) throws JSONException {
        if (jsonO.optJSONArray("answer") != null) {
            addAnswers(jsonO.getJSONArray("answer"));
        } else {
            addAnswer(jsonO.getJSONObject("answer"));
        }

    }

    /**
     * Ajout un ojbet "answer".
     * @param answerO : l'object answer
     * @throws JSONException exception JSON
     * */
    private void addAnswer(final JSONObject answerO)
            throws JSONException {
        Element answerElem = new Element("answer");
        Attribute att = new Attribute("fraction",
                answerO.getString("fraction")); /*create a new attribute*/
        answerElem.setAttribute(att);
        answerO.remove("fraction");
        Iterator <String> itKey = answerO.keys();
        while (itKey.hasNext()) {
            String key = itKey.next();
            GenGeneriqueTags.genBaseComplexElem(answerO, key, answerElem);
        }
        /*add the answer element to the questionRoot*/
        addElementToRoot(answerElem);
    }


    /**
     * "Answer" complete avec attribut fraction + feedback + text.
     * @param answerA : la liste "d'answer"
     * @throws JSONException exception JSON
     * */
    private void addAnswers(final JSONArray answerA)
            throws JSONException {
        for (int i = 0; i < answerA.length(); ++i) { /*for 2 answers*/
            /*get the iéme JSON oject*/
            JSONObject answerO = answerA.getJSONObject(i);
            addAnswer(answerO);
        }
    }

    /**
     * Ajoute tout les éléments XML
     * d'un élément Json.
     * @param jsonO : l'élément Json
     * */
    protected final void addElments(final JSONObject jsonO) {
        Iterator<String> itera = jsonO.keys();
        String currentField;
        TagsKnown knownTags = new TagsKnown();
        try {
            while (itera.hasNext()) {
                currentField = itera.next();
                if (currentField.equals("type")) {
                    Attribute att = new Attribute("type",
                            jsonO.getString("type"));
                    quest.setAttribute(att);
                } else if (currentField.equals("questiontext")) {
                    addQuestiontext(jsonO.getJSONObject("questiontext"));
                } else if (currentField.equals("answer")) {
                    preAddAnswer(jsonO); /*get the answers*/
                } else if (knownTags.isKnown(currentField)) {
                    GenGeneriqueTags.genBaseComplexElem(jsonO, currentField, quest);
                } else if (currentField.equals("shuffleanswers")) {
                    addShuffleanswers(jsonO);
                } else  {
                    warning(currentField); //balise inconnue (non repertoriée)
                    GenGeneriqueTags.genBaseComplexElem(jsonO, currentField, quest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addShuffleanswers(final JSONObject jsonO) throws JSONException {
        if (null != jsonO.optJSONArray("shuffleanswers")) {
            JSONArray jsonA = jsonO.getJSONArray("shuffleanswers");
            for (int i = 0; i < jsonA.length(); ++i) {
                Iterator<String> it = jsonA.getJSONObject(i).keys();
                while (it.hasNext()) {
                  Element shuffleElem = new Element("shuffleanswers");
                  shuffleElem.addContent(it.next());
                  addElementToRoot(shuffleElem);
                }
            }
        } else {
            addElementToRoot((createSimpleTags(
                    jsonO
                    , "shuffleanswers")));
        }
    }

    /**
     * Affiche un warning sur la sortie standard si une
     * balise inconnue est trouvé.
     * @param currentField : le nom de la balise inconnue
     * */
    private void warning(final String currentField) {
        System.out.println("WARNING : ");
        System.out.println("La balise " + currentField
                + " est non répertoriée.");
        System.out.println("La conversion continue mais aucun attribut");
        System.out.println("ne peut être créé pour cette balise.");
    }



}

package jsontoxml.xmlWriter;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Content.CType;
import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.nio.cs.Surrogate;

public class GenXML {
    
    private Element quest;
    private Set<String> simpleTags;
    private Set<String> complexTags;
    
    public GenXML(){
        quest = new Element("question");
        simpleTags = new TreeSet<String>();
        complexTags = new TreeSet<String>();
        simpleTags.add("image");
        simpleTags.add("image_base64");
        simpleTags.add("penalty");
        simpleTags.add("hidden");
        simpleTags.add("defaultgrade");
        simpleTags.add("single");
        simpleTags.add("answernumbering");
        simpleTags.add("shuffleanswers");
        simpleTags.add("usecase");
        
        complexTags.add("generalfeedback");
        complexTags.add("name");
        complexTags.add("correctfeedback");
        complexTags.add("partiallycorrectfeedback");
        complexTags.add("incorrectfeedback");
        complexTags.add("category");
        
    }
        
    public Element getQuestionElem(){
        return quest;
    }

    private void addElementToRoot(Content content){
        quest.addContent(content);
    }

    private  Element createSimpleTags(JSONObject jsonO, String name) throws JSONException{
        Element elemRet=null;
        elemRet = new Element(name);
        final String text;
        if(jsonO.isNull(name)){
            text = new String("");
        }else{
            text = jsonO.getString(name);
        }
        Content cont = new Content(CType.Text) {
            
            @Override
            public String getValue() {
                // TODO Auto-generated method stub
                return text;
            }
        };
        
        //elemRet.setText(text);
        elemRet.addContent(cont);
        return elemRet;
    }

    private Element createComplexTags(JSONObject jsonO, String name) throws JSONException{
        Element gfElem = new Element(name);
        Element textElem = createSimpleTags(jsonO, "text");
        gfElem.addContent(textElem);
        return gfElem;
    }
    
    
    private void addComplexTags(JSONObject jsonO, String name) throws JSONException{
        addElementToRoot(createComplexTags(jsonO, name));
    }


    private void addQuestiontext(final JSONObject jsonO) throws JSONException {
        String fomatValue = jsonO.getString("format");
        Element questionText = new Element("questiontext");
        Element textquestionText  = createSimpleTags(jsonO, "text");
        Attribute att = new Attribute("format", fomatValue);
        questionText.setAttribute(att);
        questionText.addContent(textquestionText);
        addElementToRoot(questionText);
    }


    private void addSubquestion(final JSONArray jsonA) throws JSONException {
        for (int i = 0; i < jsonA.length(); ++i) {
            JSONObject jsonO = jsonA.getJSONObject(i);
            Element complexElem = createComplexTags(jsonO, "subquestion");
            Element answer = createSimpleAnswer(jsonO.getJSONObject("answer"));
            complexElem.addContent(answer);
            addElementToRoot(complexElem);
        }
    }
    


    private Element createSimpleAnswer(final JSONObject answerO)
            throws JSONException {
        Element answer1 = new Element("answer");
        Element textElem = createSimpleTags(answerO, "text");
        answer1.addContent(textElem);
        return answer1;
    }
    
    /**
     * "Answer" simple que avec text
     * */

    private Element createFeedBack(final JSONObject answerO)
            throws JSONException {
        /*get the feedback object*/
        JSONObject fbO = answerO.getJSONObject("feedback");
        Element feedElem = new Element("feedback");
        Element textElemFB = createSimpleTags(fbO, "text");
        feedElem.addContent(textElemFB);
        return feedElem;
    }
    
    private void preAddAnswer(JSONObject jsonO) throws JSONException {
        if(jsonO.optJSONArray("answer") != null){
            addAnswers(jsonO.getJSONArray("answer"));
        }else{
            addAnswer(jsonO.getJSONObject("answer"));
        }
        
    }

    private void addAnswer(JSONObject answerO) throws JSONException {
        Element answerElem = createSimpleAnswer(answerO);
        Attribute att = new Attribute("fraction", answerO.getString("fraction"));/*create a new attribute*/
        answerElem.setAttribute(att);
        if(answerO.has("feedback")){
            answerElem.addContent(createFeedBack(answerO));
        }
        addElementToRoot(answerElem);/*add the answer element to the questionRoot*/
    }



    /**
     * "Answer" complete avec attribut fraction + feedback + text
     * @throws JSONException 
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
     * @param jsonO
     * */
    protected void addElments(JSONObject jsonO){
        Iterator<String> it = jsonO.keys();
        String currentField;
        try{
            while(it.hasNext()){
                currentField = it.next();
                if(currentField.equals("type")){
                 //   System.out.println( jsonO.getString("type") );
                    Attribute att = new Attribute("type", jsonO.getString("type") );       //<question type="category">
                    quest.setAttribute(att);
                }if(currentField.equals("name")){
                    addComplexTags(jsonO.getJSONObject(currentField),currentField) ;
                }else if(currentField.equals("questiontext")){
                    addQuestiontext( jsonO.getJSONObject("questiontext"));
                }else if(currentField.equals("answer")){
                    preAddAnswer(jsonO);/*get the answers*/                    
                }else if(currentField.equals("subquestion")){
                    addSubquestion( jsonO.getJSONArray("subquestion") );
                }else if(complexTags.contains(currentField)){
                    addComplexTags(jsonO.getJSONObject(currentField),currentField) ;         
                }else if(simpleTags.contains(currentField)){
                    addElementToRoot((createSimpleTags(jsonO,currentField)));
                }else if(!currentField.equals("type")){//balise inconnue (non repertoriée)
                     warning();
                    genBaseComplexElem(jsonO, currentField);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Génére les touts les éléments XML d'un JSONObject.
     * N'est utilisé que pour les balises (clé) inconnues.
     * @param jsonO : l'objet JSON de la question Moodle en cours
     * @param key : le nom de la clé inconnue
     * */
    private void genBaseComplexElem(JSONObject jsonO, String key) throws JSONException{
        if(null != jsonO.optJSONArray(key)){//si tableau
            genRecComplexElemArray(jsonO.getJSONArray(key), key, quest);
        }else{//sinon
            Element root = new Element(key);//création de l'élément racine 
            if (null != jsonO.optJSONObject(key)){//si objet complexe
                genRecComplexElem(jsonO.getJSONObject(key), key, root); //contruction de l'objet 
            }else{//sinon simple
                if(! jsonO.isNull(key)){//si valeur non null
                    root.addContent(jsonO.getString(key));//ajout de la valeur
                }else{
                    root.addContent(new String(""));//sinon ajout vide
                }
            }
            addElementToRoot(root);//si pas tableau ajout à la racine
        }
    }
    
    /**
     * Ajoute récursivement les éléments à un élément racine XML à partir d'un object JSON.
     * @param jsonO
     * */
    private void genRecComplexElem(final JSONObject jsonO
             ,String name, Element root) throws JSONException {
        Element child = null;
        @SuppressWarnings("unchecked")
        Iterator<String> it = jsonO.keys();
        while(it.hasNext()){
            String key = it.next();
            child = new Element(key);
            if(null != jsonO.optJSONArray(key)){//si tableau
                genRecComplexElemArray(jsonO.getJSONArray(key), key, root);
            }else{
                if (null != jsonO.optJSONObject(key)){//si objet
                    genRecComplexElem(jsonO.getJSONObject(key), key, child);
                }else{//sinon simple
                    if(! jsonO.isNull(key)){
                        child.addContent(jsonO.getString(key));
                    }else{
                        child.addContent(new String(""));
                    }
                }
                root.addContent(child);
            }
        }
    }

    private void genRecComplexElemArray(JSONArray jsonA, String name,
            final Element root) throws JSONException {
        Element child;
        for(int i= 0 ; jsonA.length()>i ; ++i){
            child = new Element(name);
            if(null != jsonA.optJSONArray(i)){//si tableau
                genRecComplexElemArray(jsonA.getJSONArray(i), name, child);
            }else if (null != jsonA.optJSONObject(i)){//si objet
                genRecComplexElem(jsonA.getJSONObject(i), name, child);
            }else{//sinon simple
                if(! jsonA.isNull(i)){
                    child.addContent(jsonA.getString(i));
                }else{
                    child.addContent(new String(""));
                }
            }
           root.addContent(child);
        }
    }

    private void warning(){
        System.out.println("WARNING :\nUne balise non répertorié a été trouvée.\n La conversion continue mais aucun attribut ne peut être créé pour cette balise.");
    }



}

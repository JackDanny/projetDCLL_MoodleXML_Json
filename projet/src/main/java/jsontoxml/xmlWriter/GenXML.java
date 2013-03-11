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
		
	public Element getCommonTags(){
		return quest;
	}

	private void addElementToRoot(Content content){
		quest.addContent(content);
    }
	
	private  Element createSimpleTags(JSONObject jsonO, String name) throws JSONException{
		Element elemRet=null;
		elemRet = new Element(name);
		if(!jsonO.isNull(name)){
			final String text = jsonO.getString(name);
			Content textCont = new Content(CType.Text) {
				@Override
				public String getValue() {
					return text;
				}
			};
			elemRet.addContent(textCont);
		}
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


   private void addQuestiontext(JSONObject jsonO) throws JSONException{
       String fomatValue = jsonO.getString("format");
       Element questionText = new Element("questiontext");
       Element textquestionText  = createSimpleTags(jsonO, "text");
       Attribute att = new Attribute("format", fomatValue);
       questionText.setAttribute(att);
       questionText.addContent(textquestionText);
       addElementToRoot(questionText);
	}
	

   private void addSubquestion(JSONArray jsonA) throws JSONException {
       for(int i = 0 ; i < jsonA.length() ; ++i){
           System.out.println("là");
           JSONObject jsonO = jsonA.getJSONObject(i);
           Element complexElem = createComplexTags(jsonO, "subquestion");
           Element answer = createSimpleAnswer(jsonO.getJSONObject("answer"));
           complexElem.addContent(answer);
           addElementToRoot(complexElem);
       }
   }
           
    private Element createSimpleAnswer(JSONObject answerO) throws JSONException{
        Element answer1 = new Element("answer");/*create answer Jdom element*/
        Element textElem = createSimpleTags(answerO, "text");
        answer1.addContent(textElem);
        return answer1;
    }
    
    /**
     * "Answer" simple que avec text
     * */
    private Element createFeedBack(JSONObject answerO ) throws JSONException{
        JSONObject fbO = answerO.getJSONObject("feedback");/*get the feedback object*/
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
    private void addAnswers(JSONArray answerA) throws JSONException{
        for(int i=0; i<answerA.length() ;++i){/*for 2 answers*/
            JSONObject answerO = answerA.getJSONObject(i);/*get the iéme JSON oject*/
            addAnswer(answerO);
        }
    }
    
    
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
                    addComplexTags((JSONObject) jsonO.get(currentField),currentField) ;
                }else if(currentField.equals("questiontext")){
                    addQuestiontext( jsonO.getJSONObject("questiontext"));
                }else if(currentField.equals("answer")){
                    preAddAnswer(jsonO);/*get the answers*/                    
                }else if(currentField.equals("subquestion")){
                    addSubquestion( jsonO.getJSONArray("subquestion") );
                }else if(complexTags.contains(currentField)){
                    addComplexTags((JSONObject) jsonO.get(currentField),currentField) ;         
                }else if(simpleTags.contains(currentField)){
                    addElementToRoot((createSimpleTags(jsonO,currentField)));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

 



}

package jsonToXml.xmlWriter.questionClass;

import java.util.Iterator;

import nomenclature.CommonFields;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;


public class Truefalse extends CommonTags implements Question {

	public Truefalse(){
		super("truefalse");
	}
	

	public Content getXmlContent(Object o) {
		JSONObject jo = (JSONObject) o;
		addElments(jo);
		return getCommonTags();
	}

	private void addAnswer(JSONArray answerA){
		try{
			for(int i=0; i<2 ;++i){/*for 2 answers*/
				Element answer1 = new Element("answer");/*create answer Jdom element*/
				JSONObject answerO = answerA.getJSONObject(i);/*get the iéme JSON oject*/
				JSONObject fbO = answerO.getJSONObject("feedback");/*get the feedback object*/
				Attribute att = new Attribute("fraction", answerO.getString("fraction"));/*create a new attribute*/
				answer1.setAttribute(att);
				Element textElem = createSimpleTags(answerO, "text");
				answer1.addContent(textElem);
				Element feedElem = new Element("feedback");
				Element textElemFB = createSimpleTags(fbO, "text");
				feedElem.addContent(textElemFB);
				answer1.addContent(feedElem);
				addElementToRoot(answer1);/*add the answer element to the questionRoot*/
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void addElments(JSONObject jsonO){
		Iterator<String> it = jsonO.keys();
		CommonFields  commonFields = new CommonFields(); 
		int cpt = 0;
		String currentField;
		Class<?>[] paramTypes = new Class[1];
		paramTypes[0]= JSONObject.class;
		try{
			while(it.hasNext()){
				currentField = it.next();
				if( commonFields.contains(currentField)){
					if(currentField.equals("name")){
						addName( ((JSONObject) jsonO.get("name")) );
					}else if(currentField.equals("questiontext")){
						addQuestiontext((JSONObject) jsonO.get("questiontext"));
					}else if((currentField.equals("image"))){
						addImage(jsonO,"image");
					}else if((currentField.equals("image_base64"))){
						addImage(jsonO,"image_base64");
					}else if((currentField.equals("penalty"))){
						addPenalty(jsonO) ;
					}else if((currentField.equals("generalfeedback"))){
						addGeneralfeedback((JSONObject) jsonO.get("generalfeedback")) ;
					}else if((currentField.equals("defaultgrade"))){
						addDefaultgrade(jsonO);
					}else if((currentField.equals("hidden"))){
						addHidden(jsonO);
					}
				}else if(currentField.equals("answer")){
					addAnswer(jsonO.getJSONArray("answer"));/*get the answers*/
				}else if(currentField.equals("shuffleanswers")){
					addElementToRoot( createSimpleTags(jsonO, "shuffleanswers"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
//	 <answer fraction="100">
//	    <text>true</text>
//	    <feedback><text>Correct!</text></feedback>
//	 </answer>
//	 <answer fraction="0">
//	    <text>false</text>
//	    <feedback><text>Ooops!</text></feedback>
//	 </answer>
//	
}

package jsontoxml.xmlWriter.questionClass;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import nomenclature.CommonFields;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Content.CType;
import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommonTags {
	
	private Element quest;
	
	protected CommonTags(String type){
		quest = new Element("question"); 
		Attribute att = new Attribute("type", type );		//<question type="category">
		quest.setAttribute(att);
	}
		
	protected Element getCommonTags(){
		return quest;
	}

	protected void addElementToRoot(Content content){
		quest.addContent(content);
	}
	
	protected  Element createSimpleTags(JSONObject jsonO, String name){
		Element elemRet=null;
		try{
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return elemRet;
	}
	
	
	
	protected void addPenalty(JSONObject jsonO){
		quest.addContent(createSimpleTags(jsonO, "penalty"));
	}
	
	protected void addGeneralfeedback(JSONObject jsonO){
		try{
			Element gfElem = new Element("generalfeedback");
			Element textElem = createSimpleTags(jsonO, "text");
			gfElem.addContent(textElem);
			quest.addContent(gfElem);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

   protected void addComplexTags(JSONObject jsonO, String name){
        try{
            Element gfElem = new Element(name);
            Element textElem = createSimpleTags(jsonO, "text");
            gfElem.addContent(textElem);
            quest.addContent(gfElem);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

	protected void addDefaultgrade(JSONObject jsonO){
		quest.addContent(createSimpleTags(jsonO, "defaultgrade"));
	}
	
	protected void addHidden(JSONObject jsonO){
		quest.addContent(createSimpleTags(jsonO, "hidden"));
	}
	
	protected void addName(JSONObject jsonO){
			Element name = new Element("name");
			Element textName = createSimpleTags(jsonO, "text");
			name.addContent(textName);
			quest.addContent(name);
	}

	protected void addQuestiontext(JSONObject jsonO){
		try{
			String fomatValue = jsonO.getString("format");
			final String textValue = jsonO.getString("text");
			Element questionText = new Element("questiontext");
			Element textquestionText  = createSimpleTags(jsonO, "text");
			Attribute att = new Attribute("format", fomatValue);
			questionText.setAttribute(att);
			questionText.addContent(textquestionText);
			quest.addContent(questionText);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void addImage(JSONObject jsonO, String imageCode){
			quest.addContent(createSimpleTags(jsonO,imageCode));
	}
	

    private void addAnswer(JSONArray answerA){
        try{
            for(int i=0; i<answerA.length() ;++i){/*for 2 answers*/
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
    
    
    protected void addElments(JSONObject jsonO){
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
                }else if(currentField.equals("single")){
                    addElementToRoot( createSimpleTags(jsonO, "single"));
                }else if(currentField.equals("answernumbering")){
                    addElementToRoot( createSimpleTags(jsonO, "answernumbering"));
                }else if( currentField.equals("correctfeedback") || currentField.equals("partiallycorrectfeedback") || currentField.equals("incorrectfeedback")){  
                    addComplexTags((JSONObject) jsonO.get(currentField),currentField) ;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    


}

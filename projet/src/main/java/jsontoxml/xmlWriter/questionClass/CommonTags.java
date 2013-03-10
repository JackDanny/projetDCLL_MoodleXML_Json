package jsontoxml.xmlWriter.questionClass;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Content.CType;
import org.jdom2.Element;
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
	


}

package jsonToXml.xmlWriter.questionClass;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Content.CType;
import org.jdom2.Element;

public abstract class Question {
	
	protected Element beginQuestion(String type){
		Element ques = new Element("question"); 
		Attribute att = new Attribute("type", type );
		//<question type="category">
		ques.setAttribute(att);
		return ques;
	}
	
	protected void addCommonElments(Element ques){
		Element name = new Element("name");
		Element textName = new Element("text");
		Element questionText = new Element("questiontext");
		Element textquestionText  = new Element("text");
		
		Content contNameText = new Content(CType.Text) {
			
			@Override
			public String getValue() {
				return "";//TODO
			}
		};
		
		Content contQuestText = new Content(CType.Text) {
			
			@Override
			public String getValue() {
				return "";//TODO
			}
		};
		
		textName.addContent(contNameText);
		name.addContent(textName);
		
		textquestionText.addContent(contQuestText);
		questionText.addContent(textquestionText);
		
		ques.addContent(name);
		ques.addContent(questionText);
//		<name>
//        <text>Name of question</text>
//    </name>
//    <questiontext format="html">
//        <text>What is the answer to this question?</text>
//    </questiontext>
	}
	
	public abstract Content getXmlContent(Object o);
	
}

package jsontoxml.xmlWriter.questionClass;

import org.jdom2.Content;
import org.jdom2.Content.CType;
import org.jdom2.Element;
import org.json.JSONException;
import org.json.JSONObject;

public class Category  extends CommonTags implements Question{
	
	public Category(){
		super("category");
	}
	
	
	public Content getXmlContent(Object o) {
		JSONObject jo = (JSONObject) o;
		Element ques = getCommonTags();

		try {
			final String contJson = ((JSONObject) jo.get("category")).get("text").toString();
						
			Element catElem = new Element("category");
			Element textElem = new Element("text");
			Content contText = new Content(CType.Text) {	
				@Override
				public String getValue() {
					return contJson;
				}
			};
			
			textElem.addContent(contText);
			catElem.addContent(textElem);
			ques.addContent(catElem);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
//		<category>
//		<text>$course$/Défaut pour 1SA3GL1</text>
//	</category>
//		
		return ques;
	}

	
}

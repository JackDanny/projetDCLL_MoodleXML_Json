package jsontoxml.xmlWriter.questionClass;

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

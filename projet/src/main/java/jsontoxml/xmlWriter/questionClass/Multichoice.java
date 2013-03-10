package jsontoxml.xmlWriter.questionClass;

import org.jdom2.Content;
import org.json.JSONObject;

public class Multichoice extends CommonTags implements Question {

    protected Multichoice() {
        super("multichoice");
    }

    public Content getXmlContent(Object o) {
        JSONObject jo = (JSONObject) o;
        addElments(jo);
        
        return null;
    }

}

package traducteurJson;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import xmlparser.XmlParserImpl;

public class Test {
    
    public static void main(String[] args) {
        List<Element> elems = new ArrayList<Element>();
        ToJsonTrueFalse tj =new ToJsonTrueFalse();
        
        System.out.println("deb test ToJsonTF");
        XmlParserImpl xmlparser = new XmlParserImpl();
        elems = xmlparser.parser("src/test/resources/TrueFalse.xml");
        //System.out.println(elems.size());
        tj.toJson(elems.get(0));
        System.out.println("fin test ToJsonTF");
    }

}

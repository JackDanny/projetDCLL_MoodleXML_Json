package traducteurJson;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.FactoryUtils;
import org.jdom2.Element;

import xmlToJson.jsonWriter.ToJson;
import xmlToJson.xmlparser.XmlParserImpl;
import junit.framework.TestCase;

public class ToJsonTest extends TestCase {

    public ToJsonTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testTraductionJson1(){
        List<Element> elems = new ArrayList<Element>();
        
        XmlParserImpl xmlparser = new XmlParserImpl();
        elems = xmlparser.parser("src/test/resources/TrueFalse.xml");    
        ToJson tj = new ToJson("src/test/resources/TrueFalse.json");
        tj.toJson(elems);
        assertTrue(true);
        //TODO remplacer par comparaison de deux fichiers, resultat et TrueFalseCmp.json
    }

}

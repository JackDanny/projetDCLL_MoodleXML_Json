/**
 * package test pour derniere etape XMLtoJson
 */
package jsonWriter;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import xmlToJson.jsonWriter.ToJson;
import xmlToJson.xmlparser.XmlParserImpl;
import junit.framework.TestCase;

//TODO JAVADOC + Completer les cas de tests

/**
 * Test classe ToJson assoçiée.
 * @author Raphaël
 *
 */
public class ToJsonTest extends TestCase {

    /**
     * Constructeur ToJsonTest.
     * @param name nom
     */
    public ToJsonTest(String name) {
        super(name);
    }

    /**
     * Set up.
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * tear down.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**
     * Premier Test.
     */
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

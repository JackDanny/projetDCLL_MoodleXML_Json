package xmlWriter;

import java.io.FileReader;
import java.io.Reader;

import jsontoxml.xmlWriter.XmlWriter;
import jsontoxml.xmlWriter.XmlWriterImpl;
import junit.framework.TestCase;

import org.json.JSONObject;
import org.json.JSONTokener;

public class unknownTagsTests extends TestCase {

    private JSONObject unknownTags;      
    private XmlWriter xmlWriter;
    
    /**
     * Constructor
     * @param name
     */
    public unknownTagsTests(String name) {
        super(name);
    }

    /**
     * setUp
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.xmlWriter = new XmlWriterImpl();
        Reader reader= null;
        JSONObject o2 = null;
        JSONObject tbis= null;
        try {
            reader = new FileReader("src/test/resources/USE_unknownTagsOne_RSC.json");     
            JSONTokener jsonT = new JSONTokener(reader);            
            o2 = new  JSONObject(jsonT);
            tbis = o2.getJSONObject("quiz");
            unknownTags = tbis.getJSONObject("question");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * tearDown
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        xmlWriter = null;
        unknownTags = null;
    }
    
    /**
     * Tests sur la méthode parser : non null résultat 
     */
    public void testDiff(){
       xmlWriter.writeXmlToJson(unknownTags, "src/test/resources/USE_unknownTagsOne_AUTOGEN.xml");
       
       
       
      //  assertNotNull("Erreur parser : resultat null",xmlParser.parser("src/test/resources/shortTest.xml"));    
    }
    
      
    
    
}

package xmlWriter;

import java.io.FileReader;
import java.io.Reader;

import jsontoxml.xmlWriter.XmlWriter;
import jsontoxml.xmlWriter.XmlWriterImpl;
import junit.framework.TestCase;

import org.json.JSONObject;
import org.json.JSONTokener;

public class TruefalseTests extends TestCase {

    private JSONObject trueFalseQuestion;      
    private XmlWriter xmlWriter;
    
    /**
     * Constructor
     * @param name
     */
    public TruefalseTests(String name) {
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
            reader = new FileReader("src/test/resources/USE_TruefalseOne_RSC.json");     
            JSONTokener jsonT = new JSONTokener(reader);            
            o2 = new  JSONObject(jsonT);
            tbis = o2.getJSONObject("quiz");
            trueFalseQuestion = tbis.getJSONObject("question");
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
        trueFalseQuestion = null;
    }
    
    /**
     * Tests sur la méthode parser : non null résultat 
     */
    public void testDiff(){
       xmlWriter.writeXmlToJson(trueFalseQuestion, "src/test/resources/USE_TruefalseOne_AUTOGEN.xml");
       
       
       
      //  assertNotNull("Erreur parser : resultat null",xmlParser.parser("src/test/resources/shortTest.xml"));    
    }
    
      
    
    
}

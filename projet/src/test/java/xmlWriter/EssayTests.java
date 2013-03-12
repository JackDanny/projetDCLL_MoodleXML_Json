package xmlWriter;

import java.io.FileReader;
import java.io.Reader;

import jsontoxml.xmlWriter.XmlWriter;
import jsontoxml.xmlWriter.XmlWriterImpl;
import junit.framework.TestCase;

import org.json.JSONObject;
import org.json.JSONTokener;

import filecompare.Comparateur;
import filecompare.ComparateurImpl;

public class EssayTests extends TestCase {

    private JSONObject essayQuestion;      
    private XmlWriter xmlWriter;
    private Comparateur comparator;

    
    /**
     * Constructor
     * @param name
     */
    public EssayTests(String name) {
        super(name);
    }

    /**
     * setUp
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.xmlWriter = new XmlWriterImpl();
        comparator = new ComparateurImpl();
        Reader reader= null;
        JSONObject o2 = null;
        JSONObject tbis= null;
        try {
            reader = new FileReader("src/test/resources/USE_essayOne_RSC.json");     
            JSONTokener jsonT = new JSONTokener(reader);            
            o2 = new  JSONObject(jsonT);
            tbis = o2.getJSONObject("quiz");
            essayQuestion = tbis.getJSONObject("question");
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
        essayQuestion = null;
        comparator = null;
    }
    
    /**
     * Tests sur la méthode parser : non null résultat 
     */
    public void testDiff(){
       xmlWriter.writeXmlToJson(essayQuestion, "src/test/resources/USE_essayOne_AUTOGEN.xml");
       assertTrue(comparator.compare("src/test/resources/USE_essayOne_AUTOGEN.xml","src/test/resources/USE_essayOne_RSC.xml"));
    }
    
      
    
    
}

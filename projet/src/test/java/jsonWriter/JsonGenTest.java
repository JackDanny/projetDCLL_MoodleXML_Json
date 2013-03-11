/**
 * Package des tests sur jsonWritter.
 */
package jsonWriter;

import junit.framework.TestCase;
import xmltojson.jsonWriter.JsonGen;


/**
 * Casse de Test JsonGen.
 * @author Raphaël
 *
 */
public class JsonGenTest extends TestCase {
    
    /**
     * Un JsonGen
     */
    private JsonGen jg;

    /**
     * Constructor.
     */
    public JsonGenTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor with name.
     * @param name name
     */
    public JsonGenTest(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Set up.
     */
    protected void setUp() throws Exception {
        super.setUp();
        jg = new JsonGen();
    }

    /**
     * tear down.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        jg = null;
    }
    
    /**
     * Test Constructeur.
     */
    public void testClassConstructor(){
        JsonGen jg = new JsonGen();
        assertNotNull("jsonGEn Constructor error", jg);
    }
    
    

}

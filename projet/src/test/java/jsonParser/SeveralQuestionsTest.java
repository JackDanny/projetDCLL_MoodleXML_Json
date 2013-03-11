package jsonParser;

import jsontoxml.jsonParser.JsonParser;
import jsontoxml.jsonParser.JsonParserImpl;
import junit.framework.TestCase;

public class SeveralQuestionsTest extends TestCase {


            private JsonParser jsonParser;      
            /**
             * Constructor
             * @param name
             */
            public SeveralQuestionsTest(String name) {
                super(name);
            }

            /**
             * setUp
             */
            protected void setUp() throws Exception {
                super.setUp();
                this.jsonParser = new JsonParserImpl();
            }

            /**
             * tearDown
             */
            protected void tearDown() throws Exception {
                super.tearDown();
                this.jsonParser = null;
            }
            
            /**
             * Tests sur la méthode parser : non null résultat 
             */
            public void testDiff(){
               this.jsonParser.parser("src/test/resources/USE_TruefalseArray_RSC.json");              
              //  assertNotNull("Erreur parser : resultat null",xmlParser.parser("src/test/resources/shortTest.xml"));    
            }
            
              
            
            



}

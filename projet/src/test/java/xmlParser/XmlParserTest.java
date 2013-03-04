package xmlParser;

import junit.framework.TestCase;

/**
 * Classe de test Junit du parser xml
 * @author Rapha�l
 *
 */
public class XmlParserTest extends TestCase {
	
	private XmlParser xmlParser;

	/**
	 * Constructor
	 * @param name
	 */
	public XmlParserTest(String name) {
		super(name);
	}

	/**
	 * setUp
	 */
	protected void setUp() throws Exception {
		super.setUp();
		xmlParser = new XmlParser();
	}

	/**
	 * tearDown
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		xmlParser = null;
	}
	
	/**
	 * tests sur la m�thode parser
	 */
	public void testParser(){
		//Un premier test
		assertNotNull("Erreur parser : resultat null",xmlParser.parser("exempleCourt.xml"));
		
	}

}

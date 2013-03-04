package xmlParser;

import xmlparser.XmlParserImpl;
import junit.framework.TestCase;

/**
 * Classe de test Junit du parser xml
 * @author Rapha�l
 *
 */
public class XmlParserTest extends TestCase {
	
	private XmlParserImpl xmlParser;

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
		xmlParser = new XmlParserImpl();
	}

	/**
	 * tearDown
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		xmlParser = null;
	}
	
	/**
	 * tests sur la méthode parser
	 */
	public void testParser(){
		//Un premier test
		assertNotNull("Erreur parser : resultat null",xmlParser.parser("exempleCourt.xml"));
		
	}

}

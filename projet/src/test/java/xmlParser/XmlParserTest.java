package xmlParser;

import java.util.List;
import org.jdom2.Element;

import xmlToJson.xmlparser.XmlParserImpl;
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
	 * tests sur la méthode parser : non null résultat 
	 */
	public void testNotNullParser(){
		//Vérification non null résultat
		assertNotNull("Erreur parser : resultat null",xmlParser.parser("src/test/resources/shortTest.xml"));	
	}
	
	/**
	 * Test consistance résultat
	 * */
	public void testCountListParser(){
		assertEquals(1, xmlParser.parser("src/test/resources/shortTest.xml").size());
	}
	
	/**
	 * Test l'odre des éléments "question" dans la liste.
	 * Même si ce test risque pas de devenir faux, cette
	 * propriété est très importante. Si jamais la librairie
	 * de parsing change il faut réaliser ce test.
	 * */
	public void testOrderListtParser(){
		List<Element> elemntList= xmlParser.parser("src/test/resources/orderTest.xml");
		assertEquals("category",  elemntList.get(0).getAttributeValue("type"));
		assertEquals("truefalse", elemntList.get(1).getAttributeValue("type"));
	}

	

}

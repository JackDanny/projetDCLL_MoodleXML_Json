package testSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import xmlParser.XmlParserTest;
import xmlToJson.traducteurJson.JsonGen;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Suite de test générale");
		//$JUnit-BEGIN$

		suite.addTest(new TestSuite(XmlParserTest.class));
		suite.addTest(new TestSuite(JsonGen.class));
		
		//$JUnit-END$
		return suite;
	}

}

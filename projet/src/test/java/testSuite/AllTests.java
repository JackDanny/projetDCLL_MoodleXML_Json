package testSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import traducteurJson.JsonGenTest;
import traducteurJson.ToJsonTest;
import xmlParser.XmlParserTest;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Suite de test générale");
		//$JUnit-BEGIN$

		suite.addTest(new TestSuite(XmlParserTest.class));
		suite.addTest(new TestSuite(JsonGenTest.class));
		suite.addTest(new TestSuite(ToJsonTest.class));
		
		//$JUnit-END$
		return suite;
	}

}

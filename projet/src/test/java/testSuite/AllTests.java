/**
 * Package regroupant tous nos tests.
 */
package testSuite;

import xmlParser.XmlParserTest;
import jsonWriter.ToJsonTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Comporte tous nos tests.
 * @author Raphaël
 *
 */
public class AllTests {

    /**
     * Suite complete de nos tests JUnit.
     * @return TestSuite JUnit
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Suite de test générale");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(XmlParserTest.class));
        suite.addTest(new TestSuite(ToJsonTest.class));
        suite.addTest(new xmlWriter.AllTests().suite());
        suite.addTest(new jsonParser.AllTests().suite());
        //$JUnit-END$
        return suite;
    }
}

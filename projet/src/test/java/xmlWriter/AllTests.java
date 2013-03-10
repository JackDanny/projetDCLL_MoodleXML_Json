package xmlWriter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TruefalseTests.class);
        suite.addTestSuite(MultichoiceTests.class);
        //$JUnit-END$
        return suite;
    }

}
